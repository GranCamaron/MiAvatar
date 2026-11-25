package es.studium.miavatar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;

import androidx.fragment.app.DialogFragment;

public class DialogoEspecie extends DialogFragment {

    OnDialogoListener listener;
    RadioButton rbElfo, rbEnano, rbHobbit;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnDialogoListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " debe implementar OnDialogoListener");
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View myView = inflater.inflate(R.layout.dialogo_especie, null);
        rbElfo = myView.findViewById(R.id.radioElfo);
        rbEnano = myView.findViewById(R.id.radioEnano);
        rbHobbit = myView.findViewById(R.id.radioHobbit);
        builder.setView(myView);
        builder.setTitle(R.string.dialogo_especie_titulo)
                .setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        if (rbElfo.isChecked()) {
                            listener.onEspecieElegida(0);
                        } else if (rbEnano.isChecked()) {
                            listener.onEspecieElegida(1);
                        } else if (rbHobbit.isChecked()) {
                            listener.onEspecieElegida(2);
                        } else {
                            listener.onEspecieElegida(3);
                        }
                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        listener.onDialogoCancelado();
                    }
                });
        return builder.create();
    }
}