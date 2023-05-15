package com.example.proyecto_bkd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_bkd.partida.actividades.PrincipalPartida;
import com.example.proyecto_bkd.registrarusuario.RegistroMain;
import com.example.proyecto_bkd.utils.Alert;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    public static MediaPlayer mp, puerta;
    EditText campoUsu, campoPass,eEmail;
    Button bLogin,bRegistrarse;
    public static Switch sMLogin;
    FirebaseAuth mAuth;
    TextView tRegistrar, tOlvidado,tTituloAlert,tAceptarDato;
    public static boolean music=true;
    String recuperarContrasena;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Animation animacionAbajo = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        tRegistrar=findViewById(R.id.tRegistrar);
        campoUsu = findViewById(R.id.logUsu);
        campoPass = findViewById(R.id.logPass);
        tOlvidado = findViewById(R.id.tOlividado);
        sMLogin = findViewById(R.id.sMLogin);
        bLogin=findViewById(R.id.bLogin);
        bRegistrarse=findViewById(R.id.bRegistarse);
        vibrator=(Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        mAuth = FirebaseAuth.getInstance();
        tRegistrar.setAnimation(animacionAbajo);
        mp=MediaPlayer.create(this, R.raw.alexandernakaradagatesofglory);
        puerta=MediaPlayer.create(this,R.raw.puerta);

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


                if (!email.isEmpty() && !password.isEmpty()) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        puerta.start();
                                        Toast.makeText(Login.this, getResources().getString(R.string.Bienvenido), Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Login.this, PrincipalPartida.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        vibrator.vibrate(500);
                                        Alert.alertError(Login.this,getResources().getString(R.string.ErrorInicio));
                                    }
                                }
                            });
                } else {
                    vibrator.vibrate(500);
                    Alert.alertError(Login.this,getResources().getString(R.string.CompletarCampos));
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
        tOlvidado.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurar();
            }
        });
    }
    public void restaurar(){
        AlertDialog alertRestaurar= new AlertDialog.Builder(Login.this).create();
        LayoutInflater inflater =this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_inserta_datos,null);
        alertRestaurar.setView(dialogView);
        eEmail = dialogView.findViewById(R.id.eDato);
        tTituloAlert = dialogView.findViewById(R.id.tAlertDato);
        tAceptarDato = dialogView.findViewById(R.id.tAceptarDato);
        tTituloAlert.setText(getResources().getString(R.string.RestaurarContraseña));
        eEmail.setHint(getResources().getString(R.string.IntroduceEmail));
        eEmail.setInputType(EditText.AUTOFILL_TYPE_TEXT);
        eEmail.requestFocus();
        alertRestaurar.show();
        alertRestaurar.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        tAceptarDato.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperarContrasena = eEmail.getText().toString();
                Pattern pattern = Pattern.compile("[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}",Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(recuperarContrasena);
                Log.d("boolean",matcher.matches()+"");
                if(matcher.matches()){
                    FirebaseAuth.getInstance().sendPasswordResetEmail(recuperarContrasena);
                    alertRestaurar.dismiss();
                }else{
                    vibrator.vibrate(500);
                    Alert.alertError(Login.this,getResources().getString(R.string.ErrorEmail));
                }
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