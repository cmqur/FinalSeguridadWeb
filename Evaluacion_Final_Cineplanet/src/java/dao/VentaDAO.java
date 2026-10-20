package dao;
import conexion.ConexionSQL;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import modelo.*;
public class VentaDAO {
    public int registrar(int idCliente, Carrito carrito, String medio, String usuario, String ip) throws SQLException {
        if (carrito == null || carrito.isVacio() || carrito.getIdSede() < 1 || carrito.getEntradas().size() > 10)
            throw new IllegalArgumentException("Carrito no valido.");
        if (!Arrays.asList("TARJETA","YAPE","PLIN").contains(medio)) throw new IllegalArgumentException("Medio de pago no valido.");
        Connection cn = null;
        try {
            cn = ConexionSQL.getConexion(); cn.setAutoCommit(false);
            int idVenta;
            String cabecera = "INSERT INTO Venta(idCliente,idSede,canal,medioPago,total) VALUES(?,?,'WEB',?,0)";
            try (PreparedStatement ps = cn.prepareStatement(cabecera, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1,idCliente); ps.setInt(2,carrito.getIdSede()); ps.setString(3,medio); ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) { if(!rs.next()) throw new SQLException("No se obtuvo la venta."); idVenta=rs.getInt(1); }
            }
            BigDecimal total = BigDecimal.ZERO;
            List<ItemEntrada> entradas = new ArrayList<>(carrito.getEntradas());
            entradas.sort(Comparator.comparingInt((ItemEntrada e) -> e.getFuncion().getIdFuncion()).thenComparing(ItemEntrada::getButaca));
            Set<String> repetidas = new HashSet<>();
            for (ItemEntrada item : entradas) {
                int idFuncion=item.getFuncion().getIdFuncion(); String butaca=item.getButaca();
                if (butaca==null || !butaca.matches("[A-J](?:[1-9]|1[0-2])") || !repetidas.add(idFuncion+":"+butaca))
                    throw new IllegalArgumentException("Butaca no valida o repetida.");
                BigDecimal precio;
                String sql = "SELECT precio,butacasTotales FROM Funcion WITH (UPDLOCK,HOLDLOCK) WHERE idFuncion=? AND idSede=? AND fechaHora>=GETDATE() AND fechaHora<DATEADD(DAY,7,GETDATE())";
                try (PreparedStatement ps = cn.prepareStatement(sql)) {
                    ps.setInt(1,idFuncion); ps.setInt(2,carrito.getIdSede());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) throw new IllegalArgumentException("La funcion ya no esta disponible.");
                        if ((butaca.charAt(0)-'A')*12 + Integer.parseInt(butaca.substring(1)) > rs.getInt("butacasTotales"))
                            throw new IllegalArgumentException("La butaca no pertenece a esta sala.");
                        precio=rs.getBigDecimal("precio");
                    }
                }
                try (PreparedStatement ps = cn.prepareStatement("UPDATE Funcion SET butacasDisponibles=butacasDisponibles-1 WHERE idFuncion=? AND butacasDisponibles>0")) {
                    ps.setInt(1,idFuncion); if(ps.executeUpdate()!=1) throw new IllegalArgumentException("No hay butacas disponibles.");
                }
                // El precio se lee de Funcion dentro de la transaccion; nunca del formulario.
                sql = "INSERT INTO Entrada(idCliente,idFuncion,idVenta,butaca,precioPagado) SELECT ?,idFuncion,?,?,precio FROM Funcion WHERE idFuncion=?";
                try (PreparedStatement ps = cn.prepareStatement(sql)) {
                    ps.setInt(1,idCliente); ps.setInt(2,idVenta); ps.setString(3,butaca); ps.setInt(4,idFuncion);
                    if(ps.executeUpdate()!=1) throw new SQLException("No se registro la entrada.");
                }
                total=total.add(precio);
            }
            Set<Integer> idsProductos = new HashSet<>();
            List<ItemProducto> productos = new ArrayList<>(carrito.getProductos());
            productos.sort(Comparator.comparingInt(p -> p.getProducto().getIdProducto()));
            for (ItemProducto item : productos) {
                int id=item.getProducto().getIdProducto(), cantidad=item.getCantidad();
                if(cantidad<1 || cantidad>10 || !idsProductos.add(id)) throw new IllegalArgumentException("Cantidad no valida.");
                BigDecimal precio;
                try (PreparedStatement ps = cn.prepareStatement("SELECT precio FROM Producto WITH (HOLDLOCK) WHERE idProducto=? AND activo=1")) {
                    ps.setInt(1,id); try (ResultSet rs = ps.executeQuery()) {
                        if(!rs.next()) throw new IllegalArgumentException("Un producto ya no esta disponible."); precio=rs.getBigDecimal(1);
                    }
                }
                // subtotal es calculado por SQL Server y no se inserta.
                try (PreparedStatement ps = cn.prepareStatement("INSERT INTO DetalleVenta(idVenta,idProducto,cantidad,precioUnit) VALUES(?,?,?,?)")) {
                    ps.setInt(1,idVenta); ps.setInt(2,id); ps.setInt(3,cantidad); ps.setBigDecimal(4,precio); ps.executeUpdate();
                }
                total=total.add(precio.multiply(BigDecimal.valueOf(cantidad)));
            }
            try (PreparedStatement ps = cn.prepareStatement("UPDATE Venta SET total=? WHERE idVenta=?")) {
                ps.setBigDecimal(1,total); ps.setInt(2,idVenta); ps.executeUpdate();
            }
            new AuditoriaDAO().registrar(cn,usuario,"COMPRA","Venta " + idVenta,ip);
            cn.commit(); return idVenta;
        } catch (SQLException | RuntimeException ex) {
            if(cn!=null) { try { cn.rollback(); } catch(SQLException rollback) { System.out.println("No se pudo revertir la compra."); } }
            throw ex;
        } finally {
            if(cn!=null) { try { cn.close(); } catch(SQLException cierre) { System.out.println("No se pudo cerrar la conexion."); } }
        }
    }
}
