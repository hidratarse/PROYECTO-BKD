package com.example.proyecto_bkd.resumenturno.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.Partida;
import com.example.proyecto_bkd.perfiles.PerfilesAdapter;
import com.example.proyecto_bkd.perfiles.PerfilesViewModel;
import com.example.proyecto_bkd.perfiles.actividades.ActivityDetallePerfil;
import com.example.proyecto_bkd.perfiles.actividades.ActivityPerfiles;
import com.example.proyecto_bkd.ranking.activity_ranking2;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SeleccionPerfiles extends AppCompatActivity {
    Switch sMSeleccion;
    Button bComenzar;
    ImageButton bImgPartidas,bImgPerfiles,bImgRanking;
    private RecyclerView recyclerView;
    private PerfilesAdapter adaptador;
    private PerfilesViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_perfiles);
        bComenzar =findViewById(R.id.bComenzar);
        sMSeleccion= findViewById(R.id.sMSeleccion);
        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);
        recyclerView=findViewById(R.id.rJugadores);

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
            Toast.makeText(SeleccionPerfiles.this, "Pulsado " + perfil.getEmail(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ActivityDetallePerfil.class);
            intent.putExtra("PERFIL", perfil);
            startActivity(intent);
        });

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeleccionPerfiles.this, activity_ranking2.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeleccionPerfiles.this, Partida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeleccionPerfiles.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            }
        });

        sMSeleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMSeleccion.isChecked()){
                    Login.mp.start();
                }else{
                    Login.mp.pause();
                }
            }
        });

        bComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeleccionPerfiles.this, activity_ResumenTurno.class);
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