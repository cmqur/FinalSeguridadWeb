package modelo;

public class Entrada {
    private int idEntrada;
    private int idVenta;
    private int idFuncion;
    private String titulo;
    private String sede;
    private int sala;
    private java.sql.Timestamp fechaHora;
    private String butaca;
    private java.math.BigDecimal precioPagado;
    private String estado;

    public int getIdEntrada() { return idEntrada; }
    public void setIdEntrada(int idEntrada) { this.idEntrada = idEntrada; }

    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }

    public int getIdFuncion() { return idFuncion; }
    public void setIdFuncion(int idFuncion) { this.idFuncion = idFuncion; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getSede() { return sede; }
    public void setSede(String sede) { this.sede = sede; }

    public int getSala() { return sala; }
    public void setSala(int sala) { this.sala = sala; }

    public java.sql.Timestamp getFechaHora() { return fechaHora; }
    public void setFechaHora(java.sql.Timestamp fechaHora) { this.fechaHora = fechaHora; }

    public String getButaca() { return butaca; }
    public void setButaca(String butaca) { this.butaca = butaca; }

    public java.math.BigDecimal getPrecioPagado() { return precioPagado; }
    public void setPrecioPagado(java.math.BigDecimal precioPagado) { this.precioPagado = precioPagado; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
