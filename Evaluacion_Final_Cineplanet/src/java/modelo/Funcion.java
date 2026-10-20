package modelo;

public class Funcion {
    private int idFuncion;
    private int idSede;
    private String sede;
    private String titulo;
    private int sala;
    private java.sql.Timestamp fechaHora;
    private java.math.BigDecimal precio;
    private int butacasTotales;
    private int butacasDisponibles;

    public int getIdFuncion() { return idFuncion; }
    public void setIdFuncion(int idFuncion) { this.idFuncion = idFuncion; }

    public int getIdSede() { return idSede; }
    public void setIdSede(int idSede) { this.idSede = idSede; }

    public String getSede() { return sede; }
    public void setSede(String sede) { this.sede = sede; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getSala() { return sala; }
    public void setSala(int sala) { this.sala = sala; }

    public java.sql.Timestamp getFechaHora() { return fechaHora; }
    public void setFechaHora(java.sql.Timestamp fechaHora) { this.fechaHora = fechaHora; }

    public java.math.BigDecimal getPrecio() { return precio; }
    public void setPrecio(java.math.BigDecimal precio) { this.precio = precio; }

    public int getButacasTotales() { return butacasTotales; }
    public void setButacasTotales(int butacasTotales) { this.butacasTotales = butacasTotales; }

    public int getButacasDisponibles() { return butacasDisponibles; }
    public void setButacasDisponibles(int butacasDisponibles) { this.butacasDisponibles = butacasDisponibles; }
}
