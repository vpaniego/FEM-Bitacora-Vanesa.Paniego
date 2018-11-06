package es.upm.miw.bitacora;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import es.upm.miw.bitacora.models.Repartidor;


public class RepartidorAdapter extends ArrayAdapter<Repartidor> {

    private Context context;
    private List<Repartidor> repartidores;
    private int resourceId;

    public RepartidorAdapter(@NonNull Context context, int resource, @NonNull List<Repartidor> repartidores) {
        super(context, resource, repartidores);
        this.context = context;
        this.resourceId = resource;
        this.repartidores = repartidores;
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

        Repartidor repartidor = repartidores.get(position);

        if (repartidor != null) {

            TextView tvUsername = view.findViewById(R.id.tvUsername);
            tvUsername.setText(repartidor.getUsername());

            TextView tvEmail = view.findViewById(R.id.tvEmail);
            tvEmail.setText(repartidor.getEmail());

            //TextView tvFecha = view.findViewById(R.id.tvFecha);
            //tvFecha.setText(format(repartidor.getFecha()));

        }

        return view;
    }

    public String format(Date fechaReparto) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(fechaReparto);
    }

    public void removeAllFromView() {
        this.repartidores.clear();
        notifyDataSetChanged();
    }
}
