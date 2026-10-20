package controlador;
import dao.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.Carrito;
@WebServlet("/TiendaServlet")
public class TiendaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        try {
            int id=0;
            String parametro=request.getParameter("idSede");
            if(parametro!=null) id=Integer.parseInt(parametro);
            if(id==0) id=((Carrito)request.getSession().getAttribute("carrito")).getIdSede();
            if(id==0) id=1;
            request.setAttribute("idSede",id); request.setAttribute("sedes",new SedeDAO().listar());
            request.setAttribute("funciones",new FuncionDAO().listar(id)); request.setAttribute("productos",new ProductoDAO().listar());
            request.getRequestDispatcher("tienda.jsp").forward(request,response);
        } catch(NumberFormatException ex) { response.sendError(400); }
        catch(SQLException ex) { throw new ServletException("No se pudo cargar la cartelera.",ex); }
    }
}
