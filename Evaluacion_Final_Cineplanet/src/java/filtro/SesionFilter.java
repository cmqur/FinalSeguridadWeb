package filtro;
import dao.AuditoriaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import modelo.*;
@WebFilter(urlPatterns="/*",dispatcherTypes={DispatcherType.REQUEST,DispatcherType.FORWARD})
public class SesionFilter implements Filter {
    public void init(FilterConfig config) {}
    public void destroy() {}
    public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain) throws IOException,ServletException {
        HttpServletRequest request=(HttpServletRequest)req; HttpServletResponse response=(HttpServletResponse)res;
        String ruta=request.getServletPath();
        if(ruta.equals("/") || ruta.equals("/index.jsp") || ruta.equals("/LoginServlet") || ruta.equals("/login.jsp") || ruta.equals("/error.jsp") || ruta.startsWith("/recursos/")) { chain.doFilter(req,res); return; }
        HttpSession sesion=request.getSession(false);
        Cliente cliente=sesion==null ? null : (Cliente)sesion.getAttribute("cliente");
        Usuario usuario=sesion==null ? null : (Usuario)sesion.getAttribute("usuario");
        response.setHeader("Cache-Control","no-store");
        if(cliente==null && usuario==null) { response.sendRedirect(request.getContextPath()+"/LoginServlet"); return; }
        boolean admin=ruta.equals("/VentaServlet") || ruta.equals("/AuditoriaServlet") || ruta.equals("/ventas.jsp") || ruta.equals("/auditoria.jsp");
        boolean personal=admin || ruta.equals("/panel.jsp") || ruta.equals("/AnularEntradaServlet");
        boolean salida=ruta.equals("/LogoutServlet");
        boolean permitido=salida || (personal ? usuario!=null && (!admin || "ADMIN".equals(usuario.getRol())) : cliente!=null);
        if(!permitido) {
            String cuenta=usuario!=null ? usuario.getUsuario() : cliente.getDni();
            try { new AuditoriaDAO().registrar(cuenta,"ACCESO_DENEGADO","Ruta " + ruta,request.getRemoteAddr()); }
            catch(SQLException ex) { throw new ServletException("No se pudo registrar el acceso.",ex); }
            response.sendError(403); return;
        }
        chain.doFilter(req,res);
    }
}
