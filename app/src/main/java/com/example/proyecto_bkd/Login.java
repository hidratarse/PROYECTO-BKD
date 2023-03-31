package com.example.proyecto_bkd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.proyecto_bkd.partida.Partida;
import com.example.proyecto_bkd.registrarusuario.RegistroMain;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    public static MediaPlayer mp, puerta;
    EditText campoUsu, campoPass;
    Button bLogin,bRegistrarse;
    Switch sMLogin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        campoUsu = findViewById(R.id.logUsu);
        campoPass = findViewById(R.id.logPass);
        mAuth = FirebaseAuth.getInstance();

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
                String email = campoUsu.getText().toString().trim();
                String password = campoPass.getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Login.this, Partida.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(Login.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(Login.this, "Por favor completa los campos", Toast.LENGTH_SHORT).show();
                }
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