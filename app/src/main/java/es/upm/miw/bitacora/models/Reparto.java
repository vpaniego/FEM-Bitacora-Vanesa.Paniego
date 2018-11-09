package es.upm.miw.bitacora.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;

@IgnoreExtraProperties
public class Reparto implements Serializable {

    public String id;

    public String titulo;

    public long fechaRecepcion;

    public long fechaEntrega;

    public String direccion;

    public ArrayList<Incidencia> incidencias = new ArrayList<>();

    public boolean entregado = Boolean.FALSE;

    public Reparto() {
        // Default constructor required for calls to DataSnapshot.getValue(Repartidor.class)
    }

    public Reparto(String titulo, long fechaRecepcion, long fechaEntrega, String direccion, ArrayList<Incidencia> incidencias, boolean entregado) {
        this.titulo = titulo;
        this.fechaRecepcion = fechaRecepcion;
        this.fechaEntrega = fechaEntrega;
        this.direccion = direccion;
        this.incidencias = incidencias;
        this.entregado = entregado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(ArrayList<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    @Override
    public String toString() {
        return "Reparto{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", fechaRecepcion=" + fechaRecepcion +
                ", fechaEntrega=" + fechaEntrega +
                ", direccion='" + direccion + '\'' +
                ", incidencias=" + incidencias +
                ", entregado=" + entregado +
                '}';
    }
}
