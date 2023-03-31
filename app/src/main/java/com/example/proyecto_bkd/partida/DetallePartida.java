package com.example.proyecto_bkd.partida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.ranking.activity_ranking2;

public class DetallePartida extends AppCompatActivity {
    TextView tCerrar;
    Switch sMDetallePartida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_partida);
        sMDetallePartida= findViewById(R.id.sMDetallePartida);

        sMDetallePartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMDetallePartida.isChecked()){
                    Login.mp.start();
                }else{
                    Login.mp.pause();
                }
            }
        });

        tCerrar=findViewById(R.id.tCerrar);
        tCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetallePartida.this, VerPartidas .class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        Login.mp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Login.mp.start();
    }
}