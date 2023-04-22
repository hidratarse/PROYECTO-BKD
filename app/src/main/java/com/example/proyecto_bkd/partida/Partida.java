package com.example.proyecto_bkd.partida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.perfiles.actividades.ActivityPerfiles;
import com.example.proyecto_bkd.ranking.activity_ranking2;
import com.example.proyecto_bkd.resumenturno.actividades.SeleccionPerfiles;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Partida extends AppCompatActivity {
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;
    TextView verPartida, newGame, salir, cerrarSesion, tPartida, tPerfiles, tRanking;
    public static Switch sMPartida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);
        tPartida = findViewById(R.id.tPartidas);
        tPerfiles = findViewById(R.id.tPerfiles);
        tRanking = findViewById(R.id.tRanking);
        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);
        verPartida=findViewById(R.id.tVerPartidas);
        newGame=findViewById(R.id.tNewGame);
        salir=findViewById(R.id.tSalir);
        sMPartida= findViewById(R.id.sMPartida);
        cerrarSesion = findViewById(R.id.tCerrarSesion);

        Animation animEstandarte = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_estandarte);
        tPartida.setAnimation(animEstandarte);
        tPerfiles.setAnimation(animEstandarte);
        tRanking.setAnimation(animEstandarte);
        bImgPartidas.setAnimation(animEstandarte);
        bImgPerfiles.setAnimation(animEstandarte);
        bImgRanking.setAnimation(animEstandarte);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();
            String uid = currentUser.getUid();
            // aquí podemos acceder a la infor del usuario, podemos usar su mail para relacionarlo a los perfiles y las partidas
        } else {
            // El usuario no está autenticado, debes enviarlo a la actividad de inicio de sesión
        }

        Log.d("SONANDO PARTIDA",Login.mp.isPlaying()+"");
        sMPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMPartida.isChecked()){
                    Login.mp.start();
                    Login.music=true;
                }else{
                    Login.mp.pause();
                    Login.music=false;
                }
            }
        });

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Partida.this, activity_ranking2.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Partida.this, Partida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Partida.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            }
        });
        verPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Partida.this, VerPartidas.class);
                startActivity(intent);
                finish();
            }
        });

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Partida.this, SeleccionPerfiles.class);
                startActivity(intent);
                finish();
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Partida.this, Login.class);
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
        if(Login.music){
            Login.mp.start();
        }else{
            Login.mp.pause();
            sMPartida.setChecked(false);
        }
    }
}