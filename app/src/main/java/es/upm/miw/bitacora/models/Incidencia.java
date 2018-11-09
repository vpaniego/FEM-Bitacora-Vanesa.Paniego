package es.upm.miw.bitacora.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.Random;

@IgnoreExtraProperties
public class Incidencia implements Serializable {

  public String observaciones;

    public long fecha;

    public Incidencia() {
    }

    public Incidencia(String observaciones, long fecha) {
        this.observaciones = observaciones;
        this.fecha = fecha;
    }

    private String generaID() {
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return SHA1(generatedString);
    }

    private String SHA1(String clearString) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(clearString.getBytes("UTF-8"));
            return byteArrayToString(messageDigest.digest());
        } catch (Exception ignored) {
            ignored.printStackTrace();
            return null;
        }
    }

    private static String byteArrayToString(byte[] bytes) {
        StringBuilder buffer = new StringBuilder();
        for (byte b : bytes) {
            buffer.append(String.format(Locale.getDefault(), "%02x", b));
        }
        return buffer.toString();
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Incidencia{" +
                "observaciones='" + observaciones + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
