package modelo;

public class DetalleVenta {
    private String nombre;
    private int cantidad;
    private java.math.BigDecimal precioUnit;
    private java.math.BigDecimal subtotal;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public java.math.BigDecimal getPrecioUnit() { return precioUnit; }
    public void setPrecioUnit(java.math.BigDecimal precioUnit) { this.precioUnit = precioUnit; }

    public java.math.BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(java.math.BigDecimal subtotal) { this.subtotal = subtotal; }
}
