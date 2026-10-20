package modelo;

public class Auditoria {
    private int idAuditoria;
    private java.sql.Timestamp fecha;
    private String usuario;
    private String evento;
    private String detalle;
    private String ip;

    public int getIdAuditoria() { return idAuditoria; }
    public void setIdAuditoria(int idAuditoria) { this.idAuditoria = idAuditoria; }

    public java.sql.Timestamp getFecha() { return fecha; }
    public void setFecha(java.sql.Timestamp fecha) { this.fecha = fecha; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getEvento() { return evento; }
    public void setEvento(String evento) { this.evento = evento; }

    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }

    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }
}
