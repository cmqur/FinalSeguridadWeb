package controlador;
import dao.ProductoDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/DulceriaServlet")
public class DulceriaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        try { request.setAttribute("productos",new ProductoDAO().listar()); request.getRequestDispatcher("dulceria.jsp").forward(request,response); }
        catch(SQLException ex) { throw new ServletException("No se pudo cargar la dulceria.",ex); }
    }
}
