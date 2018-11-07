package es.upm.miw.bitacora.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

@IgnoreExtraProperties
public class Repartidor {

    public String username;
    public String email;

    public ArrayList<Reparto> repartos = new ArrayList<>();

    public Repartidor() {
        // Default constructor required for calls to DataSnapshot.getValue(Repartidor.class)
    }

    public Repartidor(String username, String email, ArrayList<Reparto> repartos) {
        this.username = username;
        this.email = email;
        this.repartos = repartos;
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

    public void setRepartos(ArrayList<Reparto> repartos) {
        this.repartos = repartos;
    }

    @Override
    public String toString() {
        return "Repartidor{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", repartos=" + repartos +
                '}';
    }
}
