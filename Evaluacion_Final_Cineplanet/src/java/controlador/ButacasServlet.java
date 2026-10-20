package controlador;
import dao.FuncionDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.*;
@WebServlet("/ButacasServlet")
public class ButacasServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        try {
            int id=Integer.parseInt(request.getParameter("idFuncion")); FuncionDAO dao=new FuncionDAO(); Funcion funcion=dao.buscar(id);
            if(funcion==null) { response.sendError(404); return; }
            Set<String> ocupadas=dao.listarOcupadas(id);
            Carrito carrito=(Carrito)request.getSession().getAttribute("carrito");
            for(ItemEntrada item:carrito.getEntradas()) if(item.getFuncion().getIdFuncion()==id) ocupadas.add(item.getButaca());
            List<List<String>> filas=new ArrayList<>();
            for(char fila='A';fila<='J';fila++) { List<String> butacas=new ArrayList<>(); for(int n=1;n<=12;n++) if((fila-'A')*12+n<=funcion.getButacasTotales()) butacas.add(""+fila+n); filas.add(butacas); }
            request.setAttribute("funcion",funcion); request.setAttribute("ocupadas",ocupadas); request.setAttribute("filas",filas);
            request.getRequestDispatcher("butacas.jsp").forward(request,response);
        } catch(NumberFormatException ex) { response.sendError(400); }
        catch(SQLException ex) { throw new ServletException("No se pudo cargar la sala.",ex); }
    }
}
