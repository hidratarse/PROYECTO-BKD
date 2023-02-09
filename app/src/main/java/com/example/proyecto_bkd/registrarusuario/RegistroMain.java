package com.example.proyecto_bkd.registrarusuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;

public class RegistroMain extends AppCompatActivity {
    Button bRegistrarse, bCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_main);
        bCancelar=findViewById(R.id.bCancelar);
        bRegistrarse=findViewById(R.id.bRegistarse);

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
}