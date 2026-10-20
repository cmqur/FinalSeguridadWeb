package controlador;
import dao.VentaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.*;
import util.Csrf;
@WebServlet("/PagoServlet")
public class PagoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        request.getRequestDispatcher("pago.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8"); if(!Csrf.validar(request,response)) return;
        Cliente cliente=(Cliente)request.getSession().getAttribute("cliente"); Carrito carrito=(Carrito)request.getSession().getAttribute("carrito");
        synchronized(carrito) {
            try {
                int id=new VentaDAO().registrar(cliente.getIdCliente(),carrito,request.getParameter("medioPago"),cliente.getDni(),request.getRemoteAddr());
                carrito.vaciar(); response.sendRedirect("ComprobanteServlet?id="+id);
            } catch(IllegalArgumentException ex) { request.setAttribute("error",ex.getMessage()); request.getRequestDispatcher("pago.jsp").forward(request,response); }
            catch(SQLException ex) {
                if(ex.getErrorCode()==2601 || ex.getErrorCode()==2627) { request.setAttribute("error","Una butaca ya fue vendida. Quite esa butaca y seleccione otra."); request.getRequestDispatcher("pago.jsp").forward(request,response); }
                else throw new ServletException("No se pudo completar la compra.",ex);
            }
        }
    }
}
