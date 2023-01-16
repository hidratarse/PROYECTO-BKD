package com.example.proyecto_bkd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Partida extends AppCompatActivity {
    public static final int CODIGO_LOGIN =2;
    public static final int CODIGO_VUELTA_TEXTO = 3;
    public static final int CODIGO_REVERSO=4;
    public static final String MSG = "texto";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);
    }
}