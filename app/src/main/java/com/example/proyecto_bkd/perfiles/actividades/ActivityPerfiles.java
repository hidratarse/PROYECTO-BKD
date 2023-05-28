package com.example.proyecto_bkd.perfiles.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.actividades.PrincipalPartida;
import com.example.proyecto_bkd.perfiles.PerfilesAdapter;
import com.example.proyecto_bkd.perfiles.PerfilesViewModel;
import com.example.proyecto_bkd.ranking.Ranking;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityPerfiles extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PerfilesAdapter adaptador;
    private PerfilesViewModel vm;
    private boolean editando;
    public static final int REFRESH = 200;
    TextView tPartida, tPerfiles, tRanking;
    ImageButton bImgPartidas, bImgPerfiles, bImgRanking;
    Button nuevoPerfil;
    Switch sMPerfiles;
    LinearLayout lCargaPerfiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfiles);
        bImgPartidas = findViewById(R.id.bImgPartidas);
        bImgPerfiles = findViewById(R.id.bImgPerfiles);
        bImgRanking = findViewById(R.id.bImgRanking);
        tPartida = findViewById(R.id.tPartidas);
        tPerfiles = findViewById(R.id.tPerfiles);
        tRanking = findViewById(R.id.tRanking);
        recyclerView = findViewById(R.id.recycled_perfiles);
        sMPerfiles = findViewById(R.id.sMPerfiles);
        nuevoPerfil = findViewById(R.id.bNuevoPerfil);
        lCargaPerfiles = findViewById(R.id.lCargaPerfiles);

        Animation animEstandarte = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_estandarte);
        tPartida.setAnimation(animEstandarte);
        tPerfiles.setAnimation(animEstandarte);
        tRanking.setAnimation(animEstandarte);
        bImgPartidas.setAnimation(animEstandarte);
        bImgPerfiles.setAnimation(animEstandarte);
        bImgRanking.setAnimation(animEstandarte);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        String email = currentUser.getEmail();
        String uid = currentUser.getUid();

        if (currentUser != null) {

            // aquí podemos acceder a la infor del usuario, podemos usar su mail para relacionarlo a los perfiles y las partidas
        } else {
            // El usuario no está autenticado, debes enviarlo a la actividad de inicio de sesión
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lCargaPerfiles.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        },1500);

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

        ActivityResultLauncher activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    int codigo = result.getResultCode();

                    switch (codigo){
                        case RESULT_CANCELED:
                            break;
                        case REFRESH:
                            vm.getPerfiles(email);
                            break;
                    }
                });

        adaptador.setClickListener((view, perfil) -> {
            editando=true;
            Intent intent = new Intent(this, ActivityDetallePerfil.class);
            intent.putExtra("ID",perfil.getId());
            intent.putExtra("EDITANDO",editando);
            activityResultLauncher.launch(intent);
        });

        nuevoPerfil.setOnClickListener(view -> {
            editando=false;
            Intent intent = new Intent(this, ActivityDetallePerfil.class);
            intent.putExtra("ID"," ");
            intent.putExtra("EDITANDO",editando);
            activityResultLauncher.launch(intent);
        });

        sMPerfiles.setOnClickListener(view -> {
            if (sMPerfiles.isChecked()) {
                Login.mp.start();
                Login.music=true;
            } else {
                Login.mp.pause();
                Login.music=false;
            }
        });

        bImgRanking.setOnClickListener(view -> {
            Intent intent = new Intent(ActivityPerfiles.this, Ranking.class);
            startActivity(intent);
            finish();
        });

        bImgPartidas.setOnClickListener(view -> {
            Intent intent = new Intent(ActivityPerfiles.this, PrincipalPartida.class);
            startActivity(intent);
            finish();
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
            sMPerfiles.setChecked(false);
        }
    }
}