package com.example.proyecto_bkd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Switch;

import com.example.proyecto_bkd.partida.Partida;
import com.example.proyecto_bkd.registrarusuario.RegistroMain;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    public static MediaPlayer mp, puerta;
    Button bLogin,bRegistrarse;
    Switch sMLogin;
    FirebaseFirestore fs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fs = FirebaseFirestore.getInstance();

        Map<String, Object> users = new HashMap<>();
        users.put("prueba", "prueba");

        fs.collection("users").add(users).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("KEK","funciona");
            }
        });

        sMLogin = findViewById(R.id.sMLogin);
        mp=MediaPlayer.create(this, R.raw.alexandernakaradagatesofglory);
        puerta=MediaPlayer.create(this,R.raw.puerta);
        bLogin=findViewById(R.id.bLogin);
        bRegistrarse=findViewById(R.id.bRegistarse);

        if(!mp.isPlaying()){
            mp.setLooping(true);
            mp.start();
        }
        sMLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMLogin.isChecked()){
                    mp.start();
                }else {
                    mp.pause();

                }
            }
        });

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

    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
    }
}