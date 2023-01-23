package com.example.proyecto_bkd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class activity_perfiles extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfiles);

        recyclerView = findViewById(R.id.recycled_perfiles);

        recyclerView.setHasFixedSize(true);

       /* recyclerView.setLayoutManager(new GridLayoutManager());*/

    }
}