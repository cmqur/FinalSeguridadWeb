package controlador;
import dao.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.*;
import util.Csrf;
@WebServlet("/CarritoServlet")
public class CarritoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        request.getRequestDispatcher("carrito.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8"); if(!Csrf.validar(request,response)) return;
        Carrito carrito=(Carrito)request.getSession().getAttribute("carrito");
        synchronized(carrito) {
            String accion=request.getParameter("accion");
            try {
                if("entrada".equals(accion)) {
                    int id=Integer.parseInt(request.getParameter("idFuncion"));
                    FuncionDAO dao=new FuncionDAO(); Funcion f=dao.buscar(id);
                    if(f==null) throw new IllegalArgumentException("La funcion no esta disponible.");
                    String[] seleccion=request.getParameterValues("butaca");
                    if(seleccion==null || seleccion.length==0 || carrito.getEntradas().size()+seleccion.length>10) throw new IllegalArgumentException("Seleccione entre 1 y 10 entradas en total.");
                    if(!carrito.isVacio() && carrito.getIdSede()!=f.getIdSede()) throw new IllegalArgumentException("Vacie el carrito antes de cambiar de cine.");
                    Set<String> ocupadas=dao.listarOcupadas(id), lote=new HashSet<>();
                    for(int i=0;i<seleccion.length;i++) {
                        String b=seleccion[i].trim().toUpperCase(java.util.Locale.ROOT); seleccion[i]=b;
                        if(!b.matches("[A-J](?:[1-9]|1[0-2])")) throw new IllegalArgumentException("Formato de butaca incorrecto.");
                        if((b.charAt(0)-'A')*12+Integer.parseInt(b.substring(1))>f.getButacasTotales()) throw new IllegalArgumentException("Butaca fuera de la sala.");
                        if(ocupadas.contains(b) || carrito.contiene(id,b) || !lote.add(b)) throw new IllegalArgumentException("La butaca ya esta ocupada o en su carrito.");
                    }
                    carrito.setIdSede(f.getIdSede()); for(String b:seleccion) carrito.agregar(f,b);
                } else if("producto".equals(accion)) {
                    int id=Integer.parseInt(request.getParameter("idProducto")), cantidad=Integer.parseInt(request.getParameter("cantidad"));
                    Producto p=new ProductoDAO().buscar(id); ItemProducto existente=carrito.buscarProducto(id);
                    int total=cantidad+(existente==null ? 0 : existente.getCantidad());
                    if(p==null || cantidad<1 || cantidad>10 || total>10) throw new IllegalArgumentException("Producto o cantidad no validos (1 a 10).");
                    int sede=Integer.parseInt(request.getParameter("idSede"));
                    boolean valida=false; for(Sede s:new SedeDAO().listar()) if(s.getIdSede()==sede) valida=true;
                    if(!valida || (!carrito.isVacio() && carrito.getIdSede()!=sede)) throw new IllegalArgumentException("Seleccione el mismo cine del carrito.");
                    carrito.setIdSede(sede); carrito.agregar(p,cantidad);
                } else if("quitarEntrada".equals(accion)) {
                    carrito.quitarEntrada(Integer.parseInt(request.getParameter("idFuncion")),request.getParameter("butaca"));
                } else if("quitarProducto".equals(accion)) {
                    carrito.quitarProducto(Integer.parseInt(request.getParameter("idProducto")));
                } else if("vaciar".equals(accion)) { carrito.vaciar(); }
                else throw new IllegalArgumentException("Accion no valida.");
                if(carrito.isVacio()) carrito.setIdSede(0);
                response.sendRedirect("CarritoServlet");
            } catch(IllegalArgumentException ex) { request.setAttribute("error",ex instanceof NumberFormatException ? "Datos no validos." : ex.getMessage()); request.getRequestDispatcher("carrito.jsp").forward(request,response); }
            catch(SQLException ex) { throw new ServletException("No se pudo actualizar el carrito.",ex); }
        }
    }
}
