package dao;
import conexion.ConexionSQL;
import java.sql.*;
import java.util.*;
import modelo.Sede;
public class SedeDAO {
    public List<Sede> listar() throws SQLException {
        List<Sede> lista = new ArrayList<>();
        try (Connection cn = ConexionSQL.getConexion(); PreparedStatement ps = cn.prepareStatement("SELECT idSede, nombre, ciudad FROM Sede ORDER BY nombre"); ResultSet rs = ps.executeQuery()) {
            while(rs.next()) { Sede s = new Sede(); s.setIdSede(rs.getInt("idSede")); s.setNombre(rs.getString("nombre")); s.setCiudad(rs.getString("ciudad")); lista.add(s); }
        }
        return lista;
    }
}
