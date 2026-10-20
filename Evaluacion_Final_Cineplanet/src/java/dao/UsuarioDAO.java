package dao;
import conexion.ConexionSQL;
import java.sql.*;
import modelo.Usuario;
public class UsuarioDAO {
    public Usuario validar(String usuario, String hash) throws SQLException {
        String sql = "SELECT idUsuario, usuario, nombre, rol FROM Usuario WHERE usuario = ? AND clave = ?";
        try (Connection cn = ConexionSQL.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, usuario); ps.setString(2, hash);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { Usuario u = new Usuario(); u.setIdUsuario(rs.getInt("idUsuario")); u.setUsuario(rs.getString("usuario")); u.setNombre(rs.getString("nombre")); u.setRol(rs.getString("rol")); return u; }
            }
        }
        return null;
    }
}
