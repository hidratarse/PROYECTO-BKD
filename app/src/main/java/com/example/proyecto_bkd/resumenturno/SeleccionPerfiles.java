package com.example.proyecto_bkd.resumenturno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.Partida;

public class SeleccionPerfiles extends AppCompatActivity {
    Switch sMSeleccion;
    Button bComenzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_perfiles);
        bComenzar =findViewById(R.id.bComenzar);
        sMSeleccion= findViewById(R.id.sMSeleccion);

        sMSeleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMSeleccion.isChecked()){
                    Login.mp.start();
                }else{
                    Login.mp.pause();
                }
            }
        });

        bComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeleccionPerfiles.this, activity_ResumenTurno.class);
                startActivity(intent);
                finish();
            }
        });
    }
}