package com.example.proyecto_bkd.perfiles;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.proyecto_bkd.R;

import java.util.ArrayList;
import java.util.Arrays;

public class activity_perfiles extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Perfiles_adapter adaptador;
    private ArrayList<Perfil> perfiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfiles);

        recyclerView = findViewById(R.id.recycled_perfiles);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        perfiles = new ArrayList<>(Arrays.asList(new Perfil().generarPerfiles()));

        adaptador = new Perfiles_adapter(perfiles);

        Log.d(TAG, String.valueOf(adaptador.getItemCount()));

        recyclerView.setAdapter(adaptador);
    }
}