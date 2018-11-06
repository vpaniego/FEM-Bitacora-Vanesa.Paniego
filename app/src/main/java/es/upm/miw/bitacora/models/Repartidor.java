package es.upm.miw.bitacora.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class Repartidor {

    public String username;
    public String email;

    public List<Reparto> repartos;

    public Repartidor() {
        // Default constructor required for calls to DataSnapshot.getValue(Repartidor.class)
    }

    public Repartidor(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Reparto> getRepartos() {
        return repartos;
    }

    public void setRepartos(List<Reparto> repartos) {
        this.repartos = repartos;
    }

    @Override
    public String toString() {
        return "Repartidor{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
