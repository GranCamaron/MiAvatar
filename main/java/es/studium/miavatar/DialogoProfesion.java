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

public class DialogoProfesion extends DialogFragment {

    OnDialogoListener listener;
    RadioButton rbArquero, rbGuerrero, rbMago, rbHerrero;

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
        View myView = inflater.inflate(R.layout.dialogo_profesion, null);
        rbArquero = myView.findViewById(R.id.radioArquero);
        rbGuerrero = myView.findViewById(R.id.radioGuerrero);
        rbMago = myView.findViewById(R.id.radioMago);
        rbHerrero = myView.findViewById(R.id.radioHerrero);
        builder.setView(myView);
        builder.setTitle(R.string.dialogo_profesion_titulo)
                .setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        if (rbArquero.isChecked()) {
                            listener.onProfesionElegida(0);
                        } else if (rbGuerrero.isChecked()) {
                            listener.onProfesionElegida(1);
                        } else if (rbMago.isChecked()) {
                            listener.onProfesionElegida(2);
                        } else if (rbHerrero.isChecked()) {
                            listener.onProfesionElegida(3);
                        } else {
                            listener.onProfesionElegida(4);
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