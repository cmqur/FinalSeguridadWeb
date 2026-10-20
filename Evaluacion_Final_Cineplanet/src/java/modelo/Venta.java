package modelo;

public class Venta {
    private int idVenta;
    private int idCliente;
    private String cliente;
    private String sede;
    private java.sql.Timestamp fechaVenta;
    private String medioPago;
    private java.math.BigDecimal total;
    private String estado;
    private java.util.List<Entrada> entradas;
    private java.util.List<DetalleVenta> detalles;

    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getSede() { return sede; }
    public void setSede(String sede) { this.sede = sede; }

    public java.sql.Timestamp getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(java.sql.Timestamp fechaVenta) { this.fechaVenta = fechaVenta; }

    public String getMedioPago() { return medioPago; }
    public void setMedioPago(String medioPago) { this.medioPago = medioPago; }

    public java.math.BigDecimal getTotal() { return total; }
    public void setTotal(java.math.BigDecimal total) { this.total = total; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public java.util.List<Entrada> getEntradas() { return entradas; }
    public void setEntradas(java.util.List<Entrada> entradas) { this.entradas = entradas; }

    public java.util.List<DetalleVenta> getDetalles() { return detalles; }
    public void setDetalles(java.util.List<DetalleVenta> detalles) { this.detalles = detalles; }
}
