package controlador;
import dao.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.*;
import util.*;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final Map<String,Integer> intentos=new HashMap<>();
    private static final Map<String,Long> bloqueos=new HashMap<>();
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        HttpSession sesion=request.getSession();
        if(sesion.getAttribute("csrf")==null) Csrf.generar(sesion);
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        if(!Csrf.validar(request,response)) return;
        String tipo=request.getParameter("tipo"), cuenta=request.getParameter("cuenta"), clave=request.getParameter("clave");
        cuenta=cuenta==null ? "" : cuenta.trim().toLowerCase(java.util.Locale.ROOT);
        String identificador=tipo+":"+cuenta;
        try {
            synchronized(intentos) {
                Long hasta=bloqueos.get(identificador);
                if(hasta!=null && hasta>System.currentTimeMillis()) {
                    request.setAttribute("error","Usuario bloqueado durante 5 minutos.");
                    request.getRequestDispatcher("login.jsp").forward(request,response); return;
                }
                if(hasta!=null) { bloqueos.remove(identificador); intentos.remove(identificador); }
                Usuario usuario=null; Cliente cliente=null;
                if(clave!=null && clave.length()<=200 && !cuenta.isEmpty() && cuenta.length()<=30) {
                    String hash=Seguridad.sha256(clave);
                    if("personal".equals(tipo)) usuario=new UsuarioDAO().validar(cuenta,hash);
                    if("cliente".equals(tipo)) cliente=new ClienteDAO().validar(cuenta,hash);
                }
                if(usuario==null && cliente==null) {
                    int n=intentos.getOrDefault(identificador,0)+1; intentos.put(identificador,n);
                    new AuditoriaDAO().registrar(cuenta,"LOGIN_FALLIDO","Credenciales no validas",request.getRemoteAddr());
                    request.setAttribute("error","Usuario o clave incorrectos");
                    if(n>=3) {
                        bloqueos.put(identificador,System.currentTimeMillis()+5*60*1000L);
                        new AuditoriaDAO().registrar(cuenta,"BLOQUEO","Bloqueo de 5 minutos por 3 intentos",request.getRemoteAddr());
                        request.setAttribute("error","Usuario bloqueado durante 5 minutos.");
                    }
                    request.getRequestDispatcher("login.jsp").forward(request,response); return;
                }
                new AuditoriaDAO().registrar(cuenta,"LOGIN_OK","Inicio de sesion",request.getRemoteAddr());
                intentos.remove(identificador); bloqueos.remove(identificador);
                HttpSession anterior=request.getSession(false); if(anterior!=null) anterior.invalidate();
                HttpSession sesion=request.getSession(true); sesion.setMaxInactiveInterval(15*60); Csrf.generar(sesion);
                if(cliente!=null) { sesion.setAttribute("cliente",cliente); sesion.setAttribute("carrito",new Carrito()); response.sendRedirect("TiendaServlet"); }
                else { sesion.setAttribute("usuario",usuario); response.sendRedirect("panel.jsp"); }
            }
        } catch(SQLException ex) { throw new ServletException("No se pudo completar el ingreso.",ex); }
    }
}
