package filtro;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
@WebFilter(urlPatterns="/*",dispatcherTypes={DispatcherType.REQUEST,DispatcherType.FORWARD,DispatcherType.ERROR})
public class CabecerasFilter implements Filter {
    public void init(FilterConfig config) {}
    public void destroy() {}
    public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain) throws IOException,ServletException {
        HttpServletResponse response=(HttpServletResponse)res;
        response.setHeader("X-Frame-Options","DENY");
        response.setHeader("X-Content-Type-Options","nosniff");
        response.setHeader("Referrer-Policy","no-referrer");
        response.setHeader("Content-Security-Policy","default-src 'self'; style-src 'self'; script-src 'self'; object-src 'none'; frame-ancestors 'none'; base-uri 'self'; form-action 'self'");
        response.setHeader("Cache-Control","no-store");
        req.setCharacterEncoding("UTF-8"); res.setCharacterEncoding("UTF-8");
        chain.doFilter(req,res);
    }
}
