package dao;
import conexion.ConexionSQL;
import java.sql.*;
import java.util.*;
import modelo.Funcion;
public class FuncionDAO {
    public List<Funcion> listar(int idSede) throws SQLException {
        List<Funcion> lista = new ArrayList<>();
        String sql = "SELECT f.*, p.titulo, s.nombre AS sede FROM Funcion f JOIN Pelicula p ON p.idPelicula=f.idPelicula JOIN Sede s ON s.idSede=f.idSede WHERE f.idSede=? AND f.fechaHora>=GETDATE() AND f.fechaHora<DATEADD(DAY,7,GETDATE()) ORDER BY f.fechaHora";
        try (Connection cn = ConexionSQL.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1,idSede);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    Funcion f = new Funcion(); f.setIdFuncion(rs.getInt("idFuncion")); f.setIdSede(rs.getInt("idSede")); f.setSede(rs.getString("sede")); f.setTitulo(rs.getString("titulo")); f.setSala(rs.getInt("sala")); f.setFechaHora(rs.getTimestamp("fechaHora")); f.setPrecio(rs.getBigDecimal("precio")); f.setButacasTotales(rs.getInt("butacasTotales")); f.setButacasDisponibles(rs.getInt("butacasDisponibles")); lista.add(f);
                }
            }
        }
        return lista;
    }
    public Funcion buscar(int id) throws SQLException {
        String sql = "SELECT f.*, p.titulo, s.nombre AS sede FROM Funcion f JOIN Pelicula p ON p.idPelicula=f.idPelicula JOIN Sede s ON s.idSede=f.idSede WHERE f.idFuncion=? AND f.fechaHora>=GETDATE() AND f.fechaHora<DATEADD(DAY,7,GETDATE())";
        try (Connection cn = ConexionSQL.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1,id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) { Funcion f = new Funcion(); f.setIdFuncion(id); f.setIdSede(rs.getInt("idSede")); f.setSede(rs.getString("sede")); f.setTitulo(rs.getString("titulo")); f.setSala(rs.getInt("sala")); f.setFechaHora(rs.getTimestamp("fechaHora")); f.setPrecio(rs.getBigDecimal("precio")); f.setButacasTotales(rs.getInt("butacasTotales")); f.setButacasDisponibles(rs.getInt("butacasDisponibles")); return f; }
            }
        }
        return null;
    }
    public Set<String> listarOcupadas(int id) throws SQLException {
        Set<String> lista = new HashSet<>();
        try (Connection cn = ConexionSQL.getConexion(); PreparedStatement ps = cn.prepareStatement("SELECT butaca FROM Entrada WHERE idFuncion=? AND estado='ACTIVA'")) {
            ps.setInt(1,id); try(ResultSet rs = ps.executeQuery()) { while(rs.next()) lista.add(rs.getString("butaca")); }
        }
        return lista;
    }
}
