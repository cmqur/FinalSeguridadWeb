package dao;
import conexion.ConexionSQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Auditoria;
public class AuditoriaDAO {
    public void registrar(String usuario, String evento, String detalle, String ip) throws SQLException {
        try (Connection cn = ConexionSQL.getConexion()) { registrar(cn, usuario, evento, detalle, ip); }
    }
    public void registrar(Connection cn, String usuario, String evento, String detalle, String ip) throws SQLException {
        String sql = "INSERT INTO Auditoria (usuario, evento, detalle, ip) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, usuario == null ? "anonimo" : usuario.substring(0, Math.min(30, usuario.length())));
            ps.setString(2, evento);
            ps.setString(3, detalle == null ? "" : detalle.substring(0, Math.min(200, detalle.length())));
            ps.setString(4, ip); ps.executeUpdate();
        }
    }
    public List<Auditoria> listarUltimos100() throws SQLException {
        List<Auditoria> lista = new ArrayList<>();
        String sql = "SELECT TOP 100 idAuditoria, fecha, usuario, evento, detalle, ip FROM Auditoria ORDER BY idAuditoria DESC";
        try (Connection cn = ConexionSQL.getConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Auditoria a = new Auditoria(); a.setIdAuditoria(rs.getInt("idAuditoria")); a.setFecha(rs.getTimestamp("fecha"));
                a.setUsuario(rs.getString("usuario")); a.setEvento(rs.getString("evento")); a.setDetalle(rs.getString("detalle")); a.setIp(rs.getString("ip")); lista.add(a);
            }
        }
        return lista;
    }
}
