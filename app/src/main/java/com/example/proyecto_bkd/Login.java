package com.example.proyecto_bkd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyecto_bkd.partida.Partida;

public class Login extends AppCompatActivity {

    private static final String TAG = "";
    Button bLogin;
    static String texto=".";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bLogin=findViewById(R.id.bLogin);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Partida.class);
                intent.putExtra(texto, "Hola");
                startActivity(intent);
                finish();
            }
        });

    }
}