package controlador;
import dao.ConsultaVentaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.*;
@WebServlet("/ComprobanteServlet")
public class ComprobanteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        try {
            int id=Integer.parseInt(request.getParameter("id")); Cliente c=(Cliente)request.getSession().getAttribute("cliente");
            Venta venta=new ConsultaVentaDAO().buscarPropia(id,c.getIdCliente());
            if(venta==null) request.setAttribute("error","Compra no encontrada");
            else request.setAttribute("venta",venta);
            request.getRequestDispatcher("comprobante.jsp").forward(request,response);
        } catch(NumberFormatException ex) { request.setAttribute("error","Compra no encontrada"); request.getRequestDispatcher("comprobante.jsp").forward(request,response); }
        catch(SQLException ex) { throw new ServletException("No se pudo consultar el comprobante.",ex); }
    }
}
