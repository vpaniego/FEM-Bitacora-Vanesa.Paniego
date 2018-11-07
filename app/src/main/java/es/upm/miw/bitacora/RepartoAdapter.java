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

import es.upm.miw.bitacora.models.Reparto;


public class RepartoAdapter extends ArrayAdapter<Reparto> {

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

        Reparto reparto = repartos.get(position);

        if (reparto != null) {

            TextView tvTitle = view.findViewById(R.id.tvTitle);
            tvTitle.setText(reparto.getTitle());

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
        this.repartos.clear();
        notifyDataSetChanged();
    }
}
