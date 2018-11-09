package es.upm.miw.bitacora.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Incidencia {

    public String observaciones;

    public long fechaIncidencia;

    public Incidencia() {
    }

    public Incidencia(String observaciones, long fechaIncidencia) {
        this.observaciones = observaciones;
        this.fechaIncidencia = fechaIncidencia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public long getFechaIncidencia() {
        return fechaIncidencia;
    }

    public void setFechaIncidencia(long fechaIncidencia) {
        this.fechaIncidencia = fechaIncidencia;
    }

    @Override
    public String toString() {
        return "Incidencia{" +
                "observaciones='" + observaciones + '\'' +
                ", fechaIncidencia=" + fechaIncidencia +
                '}';
    }
}
