package controlador;
import dao.ConsultaVentaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.*;
@WebServlet("/MisEntradasServlet")
public class MisEntradasServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        Cliente c=(Cliente)request.getSession().getAttribute("cliente");
        try {
            String id=request.getParameter("id");
            if(id!=null) { Venta v=new ConsultaVentaDAO().buscarPropia(Integer.parseInt(id),c.getIdCliente()); if(v==null) request.setAttribute("error","Compra no encontrada"); else request.setAttribute("venta",v); }
            request.setAttribute("ventas",new ConsultaVentaDAO().listarPropias(c.getIdCliente())); request.getRequestDispatcher("misEntradas.jsp").forward(request,response);
        } catch(NumberFormatException ex) { response.sendError(400); }
        catch(SQLException ex) { throw new ServletException("No se pudieron consultar las compras.",ex); }
    }
}
