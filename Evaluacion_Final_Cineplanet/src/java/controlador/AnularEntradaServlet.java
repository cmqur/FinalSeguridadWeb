package controlador;
import dao.EntradaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.Usuario;
import util.Csrf;
@WebServlet("/AnularEntradaServlet")
public class AnularEntradaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8"); if(!Csrf.validar(request,response)) return;
        try {
            int id=Integer.parseInt(request.getParameter("idEntrada")); Usuario u=(Usuario)request.getSession().getAttribute("usuario");
            boolean ok=new EntradaDAO().anular(id,u.getUsuario(),request.getRemoteAddr());
            request.setAttribute(ok ? "mensaje" : "error",ok ? "Entrada anulada. La butaca vuelve a estar disponible." : "Entrada inexistente o ya anulada.");
            request.getRequestDispatcher("panel.jsp").forward(request,response);
        } catch(NumberFormatException ex) { request.setAttribute("error","Numero de entrada no valido."); request.getRequestDispatcher("panel.jsp").forward(request,response); }
        catch(SQLException ex) { throw new ServletException("No se pudo anular la entrada.",ex); }
    }
}
