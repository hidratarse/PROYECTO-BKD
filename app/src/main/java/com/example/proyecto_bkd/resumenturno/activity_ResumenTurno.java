package com.example.proyecto_bkd.resumenturno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.proyecto_bkd.R;

import java.util.ArrayList;

public class activity_ResumenTurno extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Formulario> formularios;
    ResumenTurnoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_turno);

        recyclerView = findViewById(R.id.id_rv_resumenTurnos);

        formularios = Formulario.generarFormularios();

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ResumenTurnoAdapter(formularios);

        recyclerView.setAdapter(adapter);

    }
}