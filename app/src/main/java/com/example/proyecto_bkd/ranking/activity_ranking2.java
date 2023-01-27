package com.example.proyecto_bkd.ranking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.perfiles.Perfil;

import java.util.ArrayList;
import java.util.Arrays;

public class activity_ranking2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Ranking2_adapter adapter;
    private ArrayList<Perfil> perfiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking2);

        recyclerView = findViewById(R.id.id_recycler_ranking2);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        perfiles = new ArrayList<>(Arrays.asList(new Perfil().generarPerfiles()));

        adapter = new Ranking2_adapter(perfiles);

        recyclerView.setAdapter(adapter);
    }
}