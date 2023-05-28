package com.example.proyecto_bkd.registrarusuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.utils.Alert;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistroMain extends AppCompatActivity {
    Button bRegistrarse, bCancelar;
    EditText email,pass, pass2;
    public static Switch sMRegistro;
    FirebaseFirestore fs;
    FirebaseAuth mAuth;
    Vibrator vibrator;

    private final String MENSAJE_PASS = "COMPLETA LOS DATOS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_registro_main);
        bCancelar=findViewById(R.id.bCancelar);
        bRegistrarse=findViewById(R.id.bRegistarse);
        sMRegistro= findViewById(R.id.sMRegistro);
        email = findViewById(R.id.regEmail);
        pass = findViewById(R.id.regPass);
        pass2 = findViewById(R.id.regPass2);
        vibrator=(Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

        sMRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMRegistro.isChecked()){
                    Login.mp.start();
                    Login.music =true;
                }else{
                    Login.mp.pause();
                    Login.music = false;
                }
            }
        });

        bRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUsu = email.getText().toString().trim();
                String passUsu = pass.getText().toString().trim();
                String passRepe = pass2.getText().toString();

                if ((passUsu.equals(passRepe)) && !emailUsu.isEmpty() && !passUsu.isEmpty()) {
                    registerUser(emailUsu, passUsu);
                    Intent intent = new Intent(RegistroMain.this, Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    vibrator.vibrate(500);
                    Alert.alertError(RegistroMain.this,getResources().getString(R.string.CompletarCampos));
                }
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
        if(Login.music){
            Login.mp.start();
        }else{
            Login.mp.pause();
            sMRegistro.setChecked(false);
        }
    }

    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // El registro fue exitoso
                        Intent intent = new Intent(RegistroMain.this, Login.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Si el registro falla, se mostrará un mensaje de error al usuario
                        String errorMsg = "Error al registrar el usuario: " + task.getException().getLocalizedMessage();
                        Alert.alertError(this,errorMsg);
                    }
                });
    }

}