package util;
import dao.AuditoriaDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import modelo.Cliente;
import modelo.Usuario;
public class Csrf {
    public static void generar(HttpSession sesion) { sesion.setAttribute("csrf",UUID.randomUUID().toString()); }
    public static boolean validar(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException {
        HttpSession sesion=request.getSession(false);
        Object esperado=sesion==null ? null : sesion.getAttribute("csrf");
        if(esperado instanceof String && esperado.equals(request.getParameter("csrf"))) return true;
        String usuario="anonimo";
        if(sesion!=null && sesion.getAttribute("cliente") instanceof Cliente) usuario=((Cliente)sesion.getAttribute("cliente")).getDni();
        if(sesion!=null && sesion.getAttribute("usuario") instanceof Usuario) usuario=((Usuario)sesion.getAttribute("usuario")).getUsuario();
        try { new AuditoriaDAO().registrar(usuario,"CSRF","Token ausente o incorrecto en " + request.getServletPath(),request.getRemoteAddr()); }
        catch(SQLException ex) { throw new ServletException("No se pudo registrar el rechazo.",ex); }
        response.sendError(403); return false;
    }
}
