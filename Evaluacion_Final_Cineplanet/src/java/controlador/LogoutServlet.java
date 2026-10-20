package controlador;
import dao.AuditoriaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.*;
import util.Csrf;
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException {
        request.setCharacterEncoding("UTF-8"); if(!Csrf.validar(request,response)) return;
        HttpSession sesion=request.getSession(false);
        String cuenta=sesion.getAttribute("cliente")!=null ? ((Cliente)sesion.getAttribute("cliente")).getDni() : ((Usuario)sesion.getAttribute("usuario")).getUsuario();
        try { new AuditoriaDAO().registrar(cuenta,"LOGOUT","Cierre de sesion",request.getRemoteAddr()); }
        catch(SQLException ex) { throw new ServletException("No se pudo registrar el cierre.",ex); }
        finally { sesion.invalidate(); }
        response.sendRedirect("LoginServlet");
    }
}
