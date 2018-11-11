package es.upm.miw.bitacora;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import es.upm.miw.bitacora.models.Incidencia;
import es.upm.miw.bitacora.models.Reparto;


public class IncidenciaAdapter extends ArrayAdapter<Incidencia> {

    final static String LOG_TAG = "MiW";

    private Context context;
    private List<Incidencia> incidencias;
    private int resourceId;

    public IncidenciaAdapter(@NonNull Context context, int resource, @NonNull List<Incidencia> incidencias) {
        super(context, resource, incidencias);
        this.context = context;
        this.resourceId = resource;
        this.incidencias = incidencias;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LinearLayout view;
        if (null != convertView) {
            view = (LinearLayout) convertView;
        } else {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = (LinearLayout) inflater.inflate(resourceId, parent, false);
        }

        if (incidencias != null) {
            Log.i(LOG_TAG, "Adapter getView " + incidencias.size());
        }

        Incidencia incidencia = incidencias.get(position);

        if (incidencia != null) {

            TextView tvObservaciones = view.findViewById(R.id.tvObservacionesIncidencias);
            tvObservaciones.setText(incidencia.getObservaciones());

            TextView tvFecha = view.findViewById(R.id.tvFecha);
            tvFecha.setText(format(incidencia.getFecha()));

        }

        return view;
    }

    public String format(long fechaReparto) {
        if (fechaReparto > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            return sdf.format(new Date(fechaReparto));
        } else {
            return "";
        }

    }

    public void removeAllFromView() {
        this.incidencias.clear();
        notifyDataSetChanged();
    }
}
