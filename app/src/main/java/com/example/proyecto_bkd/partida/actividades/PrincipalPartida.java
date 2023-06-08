package com.example.proyecto_bkd.partida.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.SplashScreen;
import com.example.proyecto_bkd.partida.resumenturno.actividades.SeleccionPerfiles;
import com.example.proyecto_bkd.partida.verPartida.actividades.VerPartidas;
import com.example.proyecto_bkd.perfiles.actividades.ActivityDetallePerfil;
import com.example.proyecto_bkd.perfiles.actividades.ActivityPerfiles;
import com.example.proyecto_bkd.ranking.Ranking;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PrincipalPartida extends AppCompatActivity {
    ImageButton bImgPartidas, bImgPerfiles, bImgRanking;
    TextView verPartida, newGame, salir, cerrarSesion, tPartida, tPerfiles, tRanking;
    public static Switch sMPartida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);
        tPartida = findViewById(R.id.tPartidas);
        tPerfiles = findViewById(R.id.tPerfiles);
        tRanking = findViewById(R.id.tRanking);
        bImgPartidas = findViewById(R.id.bImgPartidas);
        bImgPerfiles = findViewById(R.id.bImgPerfiles);
        bImgRanking = findViewById(R.id.bImgRanking);
        verPartida = findViewById(R.id.tVerPartidas);
        newGame = findViewById(R.id.tNewGame);
        salir = findViewById(R.id.tSalir);
        sMPartida = findViewById(R.id.sMPartida);
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

        Log.d("SONANDO PARTIDA", SplashScreen.mp.isPlaying() + "");
        sMPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sMPartida.isChecked()) {
                    SplashScreen.mp.start();
                    SplashScreen.music = true;
                } else {
                    SplashScreen.mp.pause();
                    SplashScreen.music = false;
                }
            }
        });

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipalPartida.this, Ranking.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipalPartida.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            }
        });
        verPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipalPartida.this, VerPartidas.class);
                startActivity(intent);
                finish();
            }
        });

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PrincipalPartida.this, SeleccionPerfiles.class);
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
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(PrincipalPartida.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SplashScreen.mp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SplashScreen.music) {
            SplashScreen.mp.start();
        } else {
            SplashScreen.mp.pause();
            sMPartida.setChecked(false);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PrincipalPartida.this);

        View confirmDialogView = getLayoutInflater().inflate(R.layout.alert_salir, null);
        builder.setView(confirmDialogView);

        TextView btnConfirmar = confirmDialogView.findViewById(R.id.tSalirDialog);
        TextView btnCancelar = confirmDialogView.findViewById(R.id.tCancelarDialog2);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        btnConfirmar.setOnClickListener(v -> {
            finish();
        });

        btnCancelar.setOnClickListener(v -> {
            // cancelar
            alertDialog.dismiss();
        });
        alertDialog.show();
    }
}