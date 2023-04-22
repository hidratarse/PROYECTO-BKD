package com.example.proyecto_bkd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    TextView tTituloSplas;
    ImageView iIcono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Pantalla completa
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        //Agregar animaciones
        Animation animacionArriba = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animacionAbajo = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        tTituloSplas = findViewById(R.id.tTituloSplash);
        iIcono = findViewById(R.id.iIcono);

        tTituloSplas.setAnimation(animacionAbajo);
        iIcono.setAnimation(animacionArriba);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,Login.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}