package com.example.proyecto_bkd.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.proyecto_bkd.R;

public class Alert {
    public static AlertDialog alertError;
    public static void alertError(Context context, String mensaje) {
        alertError = new AlertDialog.Builder(context).create();
        alertError.setCancelable(false);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.mensaje_error,null);
        alertError.setView(dialogView);
        TextView tAceptar = dialogView.findViewById(R.id.tAceptarError);
        TextView tMensajeError = dialogView.findViewById(R.id.tMensajeError);
        tMensajeError.setText(mensaje);
        alertError.show();
        alertError.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        tAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertError.dismiss();
            }
        });
    }
}
