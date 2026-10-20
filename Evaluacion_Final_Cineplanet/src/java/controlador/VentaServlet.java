package controlador;
import dao.ConsultaVentaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/VentaServlet")
public class VentaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        try { request.setAttribute("ventas",new ConsultaVentaDAO().listarVentas()); request.getRequestDispatcher("ventas.jsp").forward(request,response); }
        catch(SQLException ex) { throw new ServletException("No se pudo cargar la consulta.",ex); }
    }
}
