package es.upm.miw.bitacora.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Reparto {

    public String titulo;

    public long fechaRecepcion;

    public long fechaEntrega;

    public Boolean incidencia = Boolean.FALSE;

    public Reparto() {
        // Default constructor required for calls to DataSnapshot.getValue(Repartidor.class)
    }

    public Reparto(String titulo, long fechaRecepcion, long fechaEntrega, Boolean incidencia) {
        this.titulo = titulo;
        this.fechaRecepcion = fechaRecepcion;
        this.fechaEntrega = fechaEntrega;
        this.incidencia = incidencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public long getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(long fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public long getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(long fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Boolean getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Boolean incidencia) {
        this.incidencia = incidencia;
    }

    @Override
    public String toString() {
        return "Reparto{" +
                "titulo='" + titulo + '\'' +
                ", fechaRecepcion=" + fechaRecepcion +
                ", fechaEntrega=" + fechaEntrega +
                ", incidencia=" + incidencia +
                '}';
    }
}
