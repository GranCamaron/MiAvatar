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

public class DialogoSexo extends DialogFragment {

    OnDialogoListener listener;
    RadioButton rbHombre;

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
        View myView = inflater.inflate(R.layout.dialogo_sexo, null);
        rbHombre = myView.findViewById(R.id.radioHombre);
        builder.setView(myView);
        builder.setTitle(R.string.dialogo_sexo_titulo)
                .setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        if (rbHombre.isChecked()) {
                            listener.onSexoElegido(0);
                        } else {
                            listener.onSexoElegido(1);
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