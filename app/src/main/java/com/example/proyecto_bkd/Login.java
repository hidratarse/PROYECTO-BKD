package com.example.proyecto_bkd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_bkd.partida.Partida;
import com.example.proyecto_bkd.registrarusuario.RegistroMain;
import com.example.proyecto_bkd.resumenturno.actividades.activity_ResumenTurno;
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
    public static Switch sMLogin;
    FirebaseAuth mAuth;
    TextView tRegistrar,tAceptar,tMensajeError;
    public static boolean music=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Animation animacionAbajo = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        tRegistrar=findViewById(R.id.tRegistrar);
        campoUsu = findViewById(R.id.logUsu);
        campoPass = findViewById(R.id.logPass);
        mAuth = FirebaseAuth.getInstance();

        tRegistrar.setAnimation(animacionAbajo);
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
                    music=true;
                }else {
                    mp.pause();
                    music=false;
                }
            }
        });

        bLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = campoUsu.getText().toString().trim();
                String password = campoPass.getText().toString().trim();
                Vibrator vibrator =(Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

                if (!email.isEmpty() && !password.isEmpty()) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        puerta.start();
                                        Toast.makeText(Login.this, getResources().getString(R.string.Bienvenido), Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Login.this, Partida.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        vibrator.vibrate(500);
                                        alertError(getResources().getString(R.string.ErrorInicio));
                                    }
                                }
                            });
                } else {
                    vibrator.vibrate(500);
                    alertError(getResources().getString(R.string.CompletarCampos));
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

    public void alertError(String mensaje) {
        AlertDialog alertError= new AlertDialog.Builder(Login.this).create();
        LayoutInflater inflater =this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.mensaje_error,null);
        alertError.setView(dialogView);
        tAceptar = dialogView.findViewById(R.id.tAceptarError);
        tMensajeError = dialogView.findViewById(R.id.tMensajeError);
        tMensajeError.setText(mensaje);
        alertError.show();
        alertError.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        tAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertError.dismiss();            }
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