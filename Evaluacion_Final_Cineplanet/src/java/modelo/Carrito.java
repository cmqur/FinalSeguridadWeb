package modelo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
public class Carrito {
    private int idSede;
    private final List<ItemEntrada> entradas = new ArrayList<>();
    private final List<ItemProducto> productos = new ArrayList<>();
    public int getIdSede() { return idSede; }
    public void setIdSede(int idSede) { this.idSede = idSede; }
    public List<ItemEntrada> getEntradas() { return entradas; }
    public List<ItemProducto> getProductos() { return productos; }
    public boolean isVacio() { return entradas.isEmpty() && productos.isEmpty(); }
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemEntrada e : entradas) total = total.add(e.getFuncion().getPrecio());
        for (ItemProducto p : productos) total = total.add(p.getSubtotal());
        return total;
    }
    public boolean contiene(int idFuncion, String butaca) {
        for (ItemEntrada e : entradas) if (e.getFuncion().getIdFuncion() == idFuncion && e.getButaca().equals(butaca)) return true;
        return false;
    }
    public void agregar(Funcion funcion, String butaca) {
        ItemEntrada item = new ItemEntrada(); item.setFuncion(funcion); item.setButaca(butaca); entradas.add(item);
    }
    public ItemProducto buscarProducto(int id) {
        for (ItemProducto item : productos) if (item.getProducto().getIdProducto() == id) return item;
        return null;
    }
    public void agregar(Producto producto, int cantidad) {
        ItemProducto item = buscarProducto(producto.getIdProducto());
        if (item == null) { item = new ItemProducto(); item.setProducto(producto); productos.add(item); }
        item.setCantidad(item.getCantidad() + cantidad);
    }
    public void quitarEntrada(int idFuncion, String butaca) {
        entradas.removeIf(e -> e.getFuncion().getIdFuncion() == idFuncion && e.getButaca().equals(butaca));
    }
    public void quitarProducto(int idProducto) { productos.removeIf(p -> p.getProducto().getIdProducto() == idProducto); }
    public void vaciar() { entradas.clear(); productos.clear(); idSede = 0; }
}
