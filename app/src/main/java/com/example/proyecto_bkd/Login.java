package com.example.proyecto_bkd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.proyecto_bkd.partida.Partida;
import com.example.proyecto_bkd.registrarusuario.RegistroMain;

public class Login extends AppCompatActivity {
    MediaPlayer mp, puerta;
    Button bLogin,bRegistrarse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mp=MediaPlayer.create(this, R.raw.alexandernakaradagatesofglory);
        puerta=MediaPlayer.create(this,R.raw.puerta);
        bLogin=findViewById(R.id.bLogin);
        bRegistrarse=findViewById(R.id.bRegistarse);
        mp.setLooping(true);
        mp.start();

        bLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                puerta.start();
                Intent intent = new Intent(Login.this, Partida.class);
                startActivity(intent);
                finish();
            }
        });
        bRegistrarse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, RegistroMain.class);
                startActivity(intent);
                finish();
            }
        });
    }
}