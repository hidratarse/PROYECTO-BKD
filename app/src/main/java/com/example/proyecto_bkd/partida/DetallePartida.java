package com.example.proyecto_bkd.partida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.perfiles.actividades.ActivityPerfiles;
import com.example.proyecto_bkd.ranking.Ranking;
import com.example.proyecto_bkd.verPartida.VerPartidas;

import java.io.File;

public class DetallePartida extends AppCompatActivity {
    TextView tCerrar;
    Switch sMDetallePartida;
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;
    ImageView imgFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_partida);
        sMDetallePartida= findViewById(R.id.sMDetallePartida);
        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);
        imgFoto = findViewById(R.id.imgFoto);

        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "image.jpg");

        if (file.exists()) {
            Toast.makeText(this, " EXISTE", Toast.LENGTH_SHORT).show();
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imgFoto.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "NO EXISTE LA FOTO", Toast.LENGTH_SHORT).show();
        }

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