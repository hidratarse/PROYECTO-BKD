package com.example.proyecto_bkd.perfiles.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.Partida;
import com.example.proyecto_bkd.perfiles.PerfilesAdapter;
import com.example.proyecto_bkd.perfiles.PerfilesViewModel;
import com.example.proyecto_bkd.ranking.activity_ranking2;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityPerfiles extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PerfilesAdapter adaptador;
    private PerfilesViewModel vm;
    ImageButton bImgPartidas, bImgPerfiles, bImgRanking;
    Switch sMPerfiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfiles);
        bImgPartidas = findViewById(R.id.bImgPartidas);
        bImgPerfiles = findViewById(R.id.bImgPerfiles);
        bImgRanking = findViewById(R.id.bImgRanking);
        recyclerView = findViewById(R.id.recycled_perfiles);
        sMPerfiles = findViewById(R.id.sMPerfiles);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        String email = currentUser.getEmail();
        String uid = currentUser.getUid();

        if (currentUser != null) {

            // aquí podemos acceder a la infor del usuario, podemos usar su mail para relacionarlo a los perfiles y las partidas
        } else {
            // El usuario no está autenticado, debes enviarlo a la actividad de inicio de sesión
        }

        adaptador = new PerfilesAdapter();

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        recyclerView.setAdapter(adaptador);

        vm = new ViewModelProvider(this).get(PerfilesViewModel.class);
        vm.init();

        vm.getPerfiles(email);

        vm.getPerfilesLivedata().observe(this, (dato) -> {
            adaptador.setResults(dato);
        });

        adaptador.setClickListener((view, perfil) -> {
            Toast.makeText(ActivityPerfiles.this, "Pulsado " + perfil.getEmail(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ActivityDetallePerfil.class);
            intent.putExtra("PERFIL", perfil);
            startActivity(intent);
        });

        sMPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sMPerfiles.isChecked()) {
                    Login.mp.start();
                } else {
                    Login.mp.pause();
                }
            }
        });

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPerfiles.this, activity_ranking2.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPerfiles.this, Partida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPerfiles.this, ActivityPerfiles.class);
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
        Login.mp.start();
    }
}