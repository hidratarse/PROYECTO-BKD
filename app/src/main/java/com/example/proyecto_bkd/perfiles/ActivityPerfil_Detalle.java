package com.example.proyecto_bkd.perfiles;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.proyecto_bkd.R;

public class ActivityPerfil_Detalle extends AppCompatActivity {
    TextView nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_detalle);

        nombre = findViewById(R.id.tNom);

        Perfil perfil = (Perfil) getIntent().getSerializableExtra("PERFIL");

        nombre.setText(perfil.getNombre());
    }
}