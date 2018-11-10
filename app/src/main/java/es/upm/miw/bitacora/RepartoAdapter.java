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

import es.upm.miw.bitacora.models.Reparto;


public class RepartoAdapter extends ArrayAdapter<Reparto> {

    final static String LOG_TAG = "MiW";

    private Context context;
    private List<Reparto> repartos;
    private int resourceId;

    public RepartoAdapter(@NonNull Context context, int resource, @NonNull List<Reparto> repartos) {
        super(context, resource, repartos);
        this.context = context;
        this.resourceId = resource;
        this.repartos = repartos;
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

        if (repartos != null) {
            Log.i(LOG_TAG, "Adapter getView " + repartos.size());
        }

        Reparto reparto = repartos.get(position);

        if (reparto != null) {

            TextView tvTitulo = view.findViewById(R.id.tvTitulo);
            tvTitulo.setText(reparto.getTitulo());

            TextView tvFechaRepcion = view.findViewById(R.id.tvFechaRecepcion);
            tvFechaRepcion.setText(format(reparto.getFechaRecepcion()));

            TextView tvFechaEntrega = view.findViewById(R.id.tvFechaEntrega);
            tvFechaEntrega.setText(format(reparto.getFechaEntrega()));

            TextView tvDireccion = view.findViewById(R.id.tvDireccion);
            tvDireccion.setText(reparto.getDireccion());

            TextView tvEntregado = view.findViewById(R.id.tvEntregado);
            tvEntregado.setText(reparto.isEntregado() ? "SI" : "NO");
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
        this.repartos.clear();
        notifyDataSetChanged();
    }
}
