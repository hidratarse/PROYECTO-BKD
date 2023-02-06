package com.example.proyecto_bkd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.proyecto_bkd.partida.Partida;

public class Login extends AppCompatActivity {

    Button bLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*
        bLogin=findViewById(R.id.bLogin);
        bLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Partida.class);
                startActivity(intent);
                finish();
            }
        });
         */
    }
}