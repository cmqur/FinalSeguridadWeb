package controlador;
import dao.AuditoriaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/AuditoriaServlet")
public class AuditoriaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        try { request.setAttribute("eventos",new AuditoriaDAO().listarUltimos100()); request.getRequestDispatcher("auditoria.jsp").forward(request,response); }
        catch(SQLException ex) { throw new ServletException("No se pudo cargar la consulta.",ex); }
    }
}
