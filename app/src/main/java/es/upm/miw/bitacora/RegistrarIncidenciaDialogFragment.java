package es.upm.miw.bitacora;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class RegistrarIncidenciaDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final DetalleRepartoActivity activity = (DetalleRepartoActivity) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder
                .setTitle(R.string.txtDialogoRegistrarIncidenciaTitulo)
                .setMessage(R.string.txtDialogoRegistrarIncidenciaPregunta)
                .setPositiveButton(
                        R.string.txtDialogoRegistrarIncidenciaAfirmativo,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                activity.registrarIncidencia();
                            }
                        }
                )
                .setNegativeButton(
                        R.string.txtDialogoRegistrarIncidenciaNegativo,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Opcion NO: no hacer nada;
                            }
                        }
                );

        return builder.create();
    }
}
