package com.example.proyecto_bkd.resumenturno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.DetallePartida;
import com.example.proyecto_bkd.partida.VerPartidas;

public class NuevoFeudo extends AppCompatActivity {
    ImageButton mas, menos;
    TextView torres, tCancelar, tAnadir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_feudo);
        mas=findViewById(R.id.imgMas);
        menos=findViewById(R.id.imgMenos);
        torres=findViewById(R.id.tNum);
        tCancelar=findViewById(R.id.tCancelar);
        tAnadir=findViewById(R.id.tAnadir);

        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                torres.setText((Integer.parseInt(torres.getText().toString())+1)+"");
            }
        });
        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                torres.setText((Integer.parseInt(torres.getText().toString())-1)+"");
            }
        });
        tCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevoFeudo.this, activity_ResumenTurno.class);
                startActivity(intent);
                finish();
            }
        });
        tAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevoFeudo.this, activity_ResumenTurno.class);
                startActivity(intent);
                finish();
            }
        });

    }
}