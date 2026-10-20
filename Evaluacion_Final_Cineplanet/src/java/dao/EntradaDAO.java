package dao;
import conexion.ConexionSQL;
import java.sql.*;
public class EntradaDAO {
    public boolean anular(int idEntrada,String usuario,String ip) throws SQLException {
        Connection cn=null;
        try {
            cn=ConexionSQL.getConexion(); cn.setAutoCommit(false);
            int idFuncion;
            // Tomar primero el bloqueo de Funcion, igual que al comprar, para mantener el orden.
            try(PreparedStatement ps=cn.prepareStatement("SELECT f.idFuncion FROM Funcion f WITH (UPDLOCK,HOLDLOCK) JOIN Entrada e ON e.idFuncion=f.idFuncion WHERE e.idEntrada=?")) {
                ps.setInt(1,idEntrada); try(ResultSet rs=ps.executeQuery()) { if(!rs.next()) { cn.rollback(); return false; } idFuncion=rs.getInt(1); }
            }
            try(PreparedStatement ps=cn.prepareStatement("UPDATE Entrada SET estado='ANULADA' WHERE idEntrada=? AND estado='ACTIVA'")) {
                ps.setInt(1,idEntrada); if(ps.executeUpdate()!=1) { cn.rollback(); return false; }
            }
            try(PreparedStatement ps=cn.prepareStatement("UPDATE Funcion SET butacasDisponibles=butacasDisponibles+1 WHERE idFuncion=? AND butacasDisponibles<butacasTotales")) {
                ps.setInt(1,idFuncion); if(ps.executeUpdate()!=1) throw new SQLException("No se pudo devolver la butaca.");
            }
            new AuditoriaDAO().registrar(cn,usuario,"ANULACION","Entrada " + idEntrada,ip);
            cn.commit(); return true;
        } catch(SQLException ex) {
            if(cn!=null) { try { cn.rollback(); } catch(SQLException e) { System.out.println("No se pudo revertir la anulacion."); } }
            throw ex;
        } finally { if(cn!=null) { try { cn.close(); } catch(SQLException e) { System.out.println("No se pudo cerrar la conexion."); } } }
    }
}
