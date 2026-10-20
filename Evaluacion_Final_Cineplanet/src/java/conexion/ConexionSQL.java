package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexionSQL {
    private static final String URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=BD_CineplanetFinal;user=sa;password=123;encrypt=false;";
    public static Connection getConexion() throws SQLException {
        try { Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); }
        catch (ClassNotFoundException ex) { throw new SQLException("No se encontro el driver JDBC.", ex); }
        return DriverManager.getConnection(URL);
    }
}
