package dao;
import conexion.ConexionSQL;
import java.sql.*;
import modelo.Cliente;
public class ClienteDAO {
    public Cliente validar(String dni, String hash) throws SQLException {
        String sql = "SELECT idCliente, dni, nombres, apellidos, correo, telefono FROM Cliente WHERE dni = ? AND clave = ?";
        try (Connection cn = ConexionSQL.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, dni); ps.setString(2, hash);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { Cliente c = new Cliente(); c.setIdCliente(rs.getInt("idCliente")); c.setDni(rs.getString("dni")); c.setNombres(rs.getString("nombres")); c.setApellidos(rs.getString("apellidos")); c.setCorreo(rs.getString("correo")); c.setTelefono(rs.getString("telefono")); return c; }
            }
        }
        return null;
    }
}
