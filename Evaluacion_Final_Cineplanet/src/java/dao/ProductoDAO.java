package dao;
import conexion.ConexionSQL;
import java.sql.*;
import java.util.*;
import modelo.Producto;
public class ProductoDAO {
    public List<Producto> listar() throws SQLException {
        List<Producto> lista = new ArrayList<>();
        try (Connection cn = ConexionSQL.getConexion(); PreparedStatement ps = cn.prepareStatement("SELECT idProducto,nombre,categoria,precio,activo FROM Producto WHERE activo=1 ORDER BY categoria,nombre"); ResultSet rs = ps.executeQuery()) {
            while(rs.next()) { Producto p = new Producto(); p.setIdProducto(rs.getInt("idProducto")); p.setNombre(rs.getString("nombre")); p.setCategoria(rs.getString("categoria")); p.setPrecio(rs.getBigDecimal("precio")); p.setActivo(rs.getBoolean("activo")); lista.add(p); }
        }
        return lista;
    }
    public Producto buscar(int id) throws SQLException {
        try (Connection cn = ConexionSQL.getConexion(); PreparedStatement ps = cn.prepareStatement("SELECT idProducto,nombre,categoria,precio,activo FROM Producto WHERE idProducto=? AND activo=1")) {
            ps.setInt(1,id); try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) { Producto p = new Producto(); p.setIdProducto(id); p.setNombre(rs.getString("nombre")); p.setCategoria(rs.getString("categoria")); p.setPrecio(rs.getBigDecimal("precio")); p.setActivo(true); return p; }
            }
        }
        return null;
    }
}
