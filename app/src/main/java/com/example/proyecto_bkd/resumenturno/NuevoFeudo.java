package com.example.proyecto_bkd.resumenturno;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.DetallePartida;
import com.example.proyecto_bkd.partida.VerPartidas;

public class NuevoFeudo extends AppCompatActivity{

    ImageButton mas, menos;
    TextView torres, tCancelar, tAnadir;
    ImageView madera,pez,zanahoria,polvo,seta,plata,oro,cobre,diamante,perla;
    Switch sMNuevoFeudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_feudo);
        mas=findViewById(R.id.imgMas);
        menos=findViewById(R.id.imgMenos);
        torres=findViewById(R.id.tNum);
        tCancelar=findViewById(R.id.tCancelar);
        tAnadir=findViewById(R.id.tAnadir);
        madera=findViewById(R.id.imgMadera);
        pez=findViewById(R.id.imgPez);
        zanahoria=findViewById(R.id.imgZanahoria);
        polvo=findViewById(R.id.imgPolvo);
        seta=findViewById(R.id.imgSeta);
        plata=findViewById(R.id.imgPlata);
        oro=findViewById(R.id.imgOro);
        cobre=findViewById(R.id.imgCobre);
        diamante=findViewById(R.id.imgDiamante);
        perla=findViewById(R.id.imgPerla);
        sMNuevoFeudo = findViewById(R.id.sMNuevoFeudo);

        sMNuevoFeudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMNuevoFeudo.isChecked()){
                    Login.mp.start();
                }else{
                    Login.mp.pause();
                }
            }
        });

        madera.setOnClickListener(new View.OnClickListener() {
            boolean select=false;
            @Override
            public void onClick(View view) {
                if(!select){
                    madera.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    select=true;
                }else{
                    madera.setBackground(getResources().getDrawable(R.drawable.vacio));
                    select=false;
                }

            }
        });
        pez.setOnClickListener(new View.OnClickListener() {
            boolean select=false;
            @Override
            public void onClick(View view) {
                if(!select){
                    pez.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    select=true;
                }else{
                    pez.setBackground(getResources().getDrawable(R.drawable.vacio));
                    select=false;
                }

            }
        });

        zanahoria.setOnClickListener(new View.OnClickListener() {
            boolean select=false;
            @Override
            public void onClick(View view) {
                if(!select){
                    zanahoria.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    select=true;
                }else{
                    zanahoria.setBackground(getResources().getDrawable(R.drawable.vacio));
                    select=false;
                }
            }
        });
        polvo.setOnClickListener(new View.OnClickListener() {
            boolean select=false;
            @Override
            public void onClick(View view) {
                if(!select){
                    polvo.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    select=true;
                }else{
                    polvo.setBackground(getResources().getDrawable(R.drawable.vacio));
                    select=false;
                }
            }
        });
        perla.setOnClickListener(new View.OnClickListener() {
            boolean select=false;
            @Override
            public void onClick(View view) {
                if(!select){
                    perla.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    select=true;
                }else{
                    perla.setBackground(getResources().getDrawable(R.drawable.vacio));
                    select=false;
                }
            }
        });
        seta.setOnClickListener(new View.OnClickListener() {
            boolean select=false;
            @Override
            public void onClick(View view) {
                if(!select){
                    seta.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    select=true;
                }else{
                    seta.setBackground(getResources().getDrawable(R.drawable.vacio));
                    select=false;
                }
            }
        });
        oro.setOnClickListener(new View.OnClickListener() {
            boolean select=false;
            @Override
            public void onClick(View view) {
                if(!select){
                    oro.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    select=true;
                }else{
                    oro.setBackground(getResources().getDrawable(R.drawable.vacio));
                    select=false;
                }
            }
        });
        plata.setOnClickListener(new View.OnClickListener() {
            boolean select=false;
            @Override
            public void onClick(View view) {
                if(!select){
                    plata.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    select=true;
                }else{
                    plata.setBackground(getResources().getDrawable(R.drawable.vacio));
                    select=false;
                }
            }
        });
        cobre.setOnClickListener(new View.OnClickListener() {
            boolean select=false;
            @Override
            public void onClick(View view) {
                if(!select){
                    cobre.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    select=true;
                }else{
                    cobre.setBackground(getResources().getDrawable(R.drawable.vacio));
                    select=false;
                }
            }
        });
        diamante.setOnClickListener(new View.OnClickListener() {
            boolean select=false;
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if(!select){
                    diamante.setBackground(getResources().getDrawable(R.drawable.borde_recurso));

                    select=true;
                }else{
                    diamante.setBackground(getResources().getDrawable(R.drawable.vacio));
                    select=false;
                }

            }
        });

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