package com.example.proyecto_bkd.registrarusuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;

public class RegistroMain extends AppCompatActivity {
    Button bRegistrarse, bCancelar;
    Switch sMRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_main);
        bCancelar=findViewById(R.id.bCancelar);
        bRegistrarse=findViewById(R.id.bRegistarse);
        sMRegistro= findViewById(R.id.sMRegistro);

        sMRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMRegistro.isChecked()){
                    Login.mp.start();
                }else{
                    Login.mp.pause();
                }
            }
        });

        bRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroMain.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        bCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroMain.this, Login.class);
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