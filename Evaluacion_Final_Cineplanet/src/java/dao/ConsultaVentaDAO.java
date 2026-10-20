package dao;
import conexion.ConexionSQL;
import java.sql.*;
import java.util.*;
import modelo.*;
public class ConsultaVentaDAO {
    public Venta buscarPropia(int idVenta, int idCliente) throws SQLException {
        Venta v = null;
        String sql = "SELECT v.*,s.nombre AS sede,c.nombres+' '+c.apellidos AS cliente FROM Venta v JOIN Sede s ON s.idSede=v.idSede JOIN Cliente c ON c.idCliente=v.idCliente WHERE v.idVenta=? AND v.idCliente=?";
        try (Connection cn = ConexionSQL.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1,idVenta); ps.setInt(2,idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) { v=new Venta(); v.setIdVenta(idVenta); v.setIdCliente(idCliente); v.setSede(rs.getString("sede")); v.setCliente(rs.getString("cliente")); v.setFechaVenta(rs.getTimestamp("fechaVenta")); v.setMedioPago(rs.getString("medioPago")); v.setTotal(rs.getBigDecimal("total")); v.setEstado(rs.getString("estado")); }
            }
            if(v==null) return null;
            List<Entrada> entradas = new ArrayList<>();
            sql="SELECT e.*,p.titulo,f.sala,f.fechaHora FROM Entrada e JOIN Funcion f ON f.idFuncion=e.idFuncion JOIN Pelicula p ON p.idPelicula=f.idPelicula JOIN Venta v ON v.idVenta=e.idVenta WHERE v.idVenta=? AND v.idCliente=?";
            try(PreparedStatement pe=cn.prepareStatement(sql)) {
                pe.setInt(1,idVenta); pe.setInt(2,idCliente);
                try(ResultSet rs=pe.executeQuery()) { while(rs.next()) { Entrada e=new Entrada(); e.setIdEntrada(rs.getInt("idEntrada")); e.setIdVenta(idVenta); e.setIdFuncion(rs.getInt("idFuncion")); e.setTitulo(rs.getString("titulo")); e.setSala(rs.getInt("sala")); e.setFechaHora(rs.getTimestamp("fechaHora")); e.setButaca(rs.getString("butaca")); e.setPrecioPagado(rs.getBigDecimal("precioPagado")); e.setEstado(rs.getString("estado")); entradas.add(e); } }
            }
            v.setEntradas(entradas);
            List<DetalleVenta> detalles=new ArrayList<>();
            sql="SELECT p.nombre,d.cantidad,d.precioUnit,d.subtotal FROM DetalleVenta d JOIN Producto p ON p.idProducto=d.idProducto JOIN Venta v ON v.idVenta=d.idVenta WHERE v.idVenta=? AND v.idCliente=?";
            try(PreparedStatement pd=cn.prepareStatement(sql)) {
                pd.setInt(1,idVenta); pd.setInt(2,idCliente);
                try(ResultSet rs=pd.executeQuery()) { while(rs.next()) { DetalleVenta d=new DetalleVenta(); d.setNombre(rs.getString("nombre")); d.setCantidad(rs.getInt("cantidad")); d.setPrecioUnit(rs.getBigDecimal("precioUnit")); d.setSubtotal(rs.getBigDecimal("subtotal")); detalles.add(d); } }
            }
            v.setDetalles(detalles);
        }
        return v;
    }
    public List<Venta> listarPropias(int idCliente) throws SQLException {
        List<Venta> lista=new ArrayList<>();
        String sql="SELECT TOP 100 v.*,s.nombre AS sede FROM Venta v JOIN Sede s ON s.idSede=v.idSede WHERE v.idCliente=? ORDER BY v.idVenta DESC";
        try(Connection cn=ConexionSQL.getConexion(); PreparedStatement ps=cn.prepareStatement(sql)) {
            ps.setInt(1,idCliente);
            try(ResultSet rs=ps.executeQuery()) { while(rs.next()) { Venta v=new Venta(); v.setIdVenta(rs.getInt("idVenta")); v.setSede(rs.getString("sede")); v.setFechaVenta(rs.getTimestamp("fechaVenta")); v.setTotal(rs.getBigDecimal("total")); v.setEstado(rs.getString("estado")); lista.add(v); } }
        }
        return lista;
    }
    public List<Venta> listarVentas() throws SQLException {
        List<Venta> lista=new ArrayList<>();
        String sql="SELECT TOP 100 v.*,s.nombre AS sede,c.nombres+' '+c.apellidos AS cliente FROM Venta v JOIN Sede s ON s.idSede=v.idSede JOIN Cliente c ON c.idCliente=v.idCliente ORDER BY v.idVenta DESC";
        try(Connection cn=ConexionSQL.getConexion(); PreparedStatement ps=cn.prepareStatement(sql); ResultSet rs=ps.executeQuery()) {
            while(rs.next()) { Venta v=new Venta(); v.setIdVenta(rs.getInt("idVenta")); v.setCliente(rs.getString("cliente")); v.setSede(rs.getString("sede")); v.setFechaVenta(rs.getTimestamp("fechaVenta")); v.setMedioPago(rs.getString("medioPago")); v.setTotal(rs.getBigDecimal("total")); v.setEstado(rs.getString("estado")); lista.add(v); }
        }
        return lista;
    }
}
