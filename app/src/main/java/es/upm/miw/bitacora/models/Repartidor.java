package es.upm.miw.bitacora.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Repartidor {

    public String username;
    public String email;

    public Repartidor() {
        // Default constructor required for calls to DataSnapshot.getValue(Repartidor.class)
    }

    public Repartidor(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
