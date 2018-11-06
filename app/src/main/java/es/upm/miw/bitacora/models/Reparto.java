package es.upm.miw.bitacora.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Reparto {

    public String title;

    public Reparto() {
        // Default constructor required for calls to DataSnapshot.getValue(Repartidor.class)
    }

    public Reparto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Reparto{" +
                "title='" + title + '\'' +
                '}';
    }
}
