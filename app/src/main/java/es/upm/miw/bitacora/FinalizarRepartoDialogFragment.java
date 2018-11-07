package es.upm.miw.bitacora;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class FinalizarRepartoDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final RepartosActivity activity = (RepartosActivity) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder
                .setTitle(R.string.txtDialogoFinalizarRepartoTitulo)
                .setMessage(R.string.txtDialogoFinalizarRepartoPregunta)
                .setPositiveButton(
                        R.string.txtDialogoFinalizarRepartoAfirmativo,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //String messageEliminados = "Eliminados " + String.valueOf(activity.resultadoRepository.removeAll()) + " resultados";
                                Toast.makeText(activity.getBaseContext(), "Finalizar reparto.Entrega satistactoria",
                                        Toast.LENGTH_SHORT).show();
                                //activity.adapter.removeAllFromView();
                            }
                        }
                )
                .setNegativeButton(
                        R.string.txtDialogoFinalizarRepartoNegativo,
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
