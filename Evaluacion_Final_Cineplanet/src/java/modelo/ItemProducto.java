package modelo;

public class ItemProducto {
    private Producto producto;
    private int cantidad;

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public java.math.BigDecimal getSubtotal() { return producto.getPrecio().multiply(java.math.BigDecimal.valueOf(cantidad)); }
}
