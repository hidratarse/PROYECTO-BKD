package com.example.proyecto_bkd.partida.verPartida.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.PartidasViewModel;
import com.example.proyecto_bkd.partida.actividades.PantallaPartida;
import com.example.proyecto_bkd.perfiles.actividades.ActivityPerfiles;
import com.example.proyecto_bkd.ranking.Ranking;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DetallePartida extends AppCompatActivity {
    TextView tCerrar,tFechaP,tJug1,tJug2,tJug3,tJug4,tColor1,tColor2,tColor3,tColor4;
    TextView tPtos1, tPtos2,tPtos3,tPtos4,tPosicion1,tPosicion2,tPosicion3,tPosicion4;
    Switch sMDetallePartida;
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;
    ImageView imgFoto;
    PartidasViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_partida);
        sMDetallePartida= findViewById(R.id.sMDetallePartida);
        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);
        imgFoto = findViewById(R.id.imgFoto);
        tFechaP = findViewById(R.id.tFechaP);
        tJug1 = findViewById(R.id.tJug1);
        tJug2 = findViewById(R.id.tJug2);
        tJug3 = findViewById(R.id.tJug3);
        tJug4 = findViewById(R.id.tJug4);
        tColor1 = findViewById(R.id.tColor1);
        tColor2 = findViewById(R.id.tColor2);
        tColor3 = findViewById(R.id.tColor3);
        tColor4 = findViewById(R.id.tColor4);
        tPtos1 = findViewById(R.id.tPtos);
        tPtos2 = findViewById(R.id.tPtos1);
        tPtos3= findViewById(R.id.tPtos2);
        tPtos4 = findViewById(R.id.tPtos3);
        tPosicion1 = findViewById(R.id.tPoscion1);
        tPosicion2 = findViewById(R.id.tPoscion2);
        tPosicion3 = findViewById(R.id.tPoscion3);
        tPosicion4 = findViewById(R.id.tPoscion4);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String email = currentUser.getEmail();

        vm = new ViewModelProvider(this).get(PartidasViewModel.class);
        vm.init();
        String idPartida = getIntent().getStringExtra("ID");
        Log.d("IDDDDDD",idPartida);

        vm.getPartida(idPartida);

        /*
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "image.jpg");

        if (file.exists()) {
            Toast.makeText(this, " EXISTE", Toast.LENGTH_SHORT).show();
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imgFoto.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "NO EXISTE LA FOTO", Toast.LENGTH_SHORT).show();
        }
        */
        vm.getPartidaLivedata().observe(this,partidas -> {
            tFechaP.setText(partidas.getFecha()+"");
            tJug1.setText(partidas.getJ1().getNomJugador());
            tJug2.setText(partidas.getJ2().getNomJugador());
            tColor1.setText(partidas.getJ1().getColor());
            tColor2.setText(partidas.getJ2().getColor());
            tPtos1.setText(String.valueOf(partidas.getJ1().getPuntos()));
            tPtos2.setText(String.valueOf(partidas.getJ2().getPuntos()));
            tPosicion1.setText(String.valueOf(partidas.getJ1().getPosicion()));
            tPosicion2.setText((String.valueOf(partidas.getJ2().getPosicion())));
            if(partidas.getJ3()!=null){
                tJug3.setText(partidas.getJ3().getNomJugador());
                tColor3.setText(partidas.getJ3().getColor());
                tPtos3.setText(String.valueOf(partidas.getJ3().getPuntos()));
                tPosicion3.setText((String.valueOf(partidas.getJ3().getPosicion())));
            }else{
                tJug3.setText("");
                tColor3.setText("");
                tPtos3.setText("");
                tPosicion3.setText("");
            }
            if(partidas.getJ4()!=null) {
                tJug4.setText(partidas.getJ4().getNomJugador());
                tColor4.setText(partidas.getJ4().getColor());
                tPtos4.setText(String.valueOf(partidas.getJ4().getPuntos()));
                tPosicion4.setText((String.valueOf(partidas.getJ4().getPosicion())));
            }else{
                tJug4.setText("");
                tColor4.setText("");
                tPtos4.setText("");
                tPosicion4.setText("");
            }
        });

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetallePartida.this, Ranking.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetallePartida.this, PantallaPartida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetallePartida.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            }
        });

        sMDetallePartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMDetallePartida.isChecked()){
                    Login.mp.start();
                    Login.music =true;
                }else {
                    Login.mp.pause();
                    Login.music = false;
                }
            }
        });

        tCerrar=findViewById(R.id.tCerrar);
        tCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetallePartida.this, VerPartidas.class);
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
            sMDetallePartida.setChecked(false);
        }
    }
}