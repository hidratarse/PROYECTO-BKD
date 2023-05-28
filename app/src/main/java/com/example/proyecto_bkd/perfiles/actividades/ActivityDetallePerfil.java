package com.example.proyecto_bkd.perfiles.actividades;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.actividades.PrincipalPartida;
import com.example.proyecto_bkd.perfiles.PerfilesViewModel;
import com.example.proyecto_bkd.perfiles.data.Perfil;
import com.example.proyecto_bkd.ranking.Ranking;
import com.example.proyecto_bkd.utils.Alert;
import com.example.proyecto_bkd.utils.GallerySaver;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class ActivityDetallePerfil extends AppCompatActivity {
    EditText nombre;
    ImageButton bImgPartidas, bImgPerfiles, bImgRanking, perfilImg;
    TextView tCancela, tModificar, tInsertar, nPartidas, nGanadas, nPuntuacion, tBorrar;
    Switch sMPerfilDetalle;
    PerfilesViewModel vm;
    private Uri pfpUri;
    private String currentPfpUrl = "";
    ActivityResultLauncher<Intent> galleryResult;
    ActivityResultLauncher<String> galleryLauncher;
    ActivityResultLauncher<String> camaraLauncher;
    ActivityResultLauncher<Intent> camaraResult;
    boolean perfilValido;
    int nuevaAnchura = 456;
    int nuevaAltura = 494;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_detalle);
        bImgPartidas = findViewById(R.id.bImgPartidas);
        bImgPerfiles = findViewById(R.id.bImgPerfiles);
        bImgRanking = findViewById(R.id.bImgRanking);
        nombre = findViewById(R.id.eNombre);
        tCancela = findViewById(R.id.tCancela);
        tModificar = findViewById(R.id.tModificar);
        tInsertar = findViewById(R.id.tInsertar);
        tBorrar = findViewById(R.id.tBorrar);
        sMPerfilDetalle = findViewById(R.id.sMPerfilDetalle);
        nPartidas = findViewById(R.id.NumPartidasJugadas);
        nGanadas = findViewById(R.id.NumPartidasGanadas);
        nPuntuacion = findViewById(R.id.NumMaxPuntuacion);
        perfilImg = findViewById(R.id.imgPerfil);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String email = currentUser.getEmail();

        vm = new ViewModelProvider(this).get(PerfilesViewModel.class);
        vm.init();

        galleryLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                iniciarGaleria();
            } else {
                // explicar por que se necesita
            }
        });

        camaraLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                iniciarCamara();
            } else {
                // explicar por que se necesita
            }
        });

        galleryResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            switch (result.getResultCode()) {
                case RESULT_CANCELED:
                    break;
                case RESULT_OK:
                    Intent data = result.getData();
                    pfpUri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), pfpUri);
                        Bitmap imagenRedimensionada = Bitmap.createScaledBitmap(bitmap, nuevaAnchura, nuevaAltura, true);
                        String nombre ="";

                        for (int i = 0; i < 8; i++) {
                            nombre+= (int)(Math.random()*10);
                        }
                        String nombreArchivo = nombre+".jpg";
                        nombre ="";
                        ContentValues valores = new ContentValues();
                        valores.put(MediaStore.Images.Media.DISPLAY_NAME, nombreArchivo);
                        valores.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            valores.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);
                        }

                        Uri uriImagenRedimensionada = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, valores);

                        // Comprime y guarda la imagen redimensionada en la URI obtenida
                        OutputStream outputStream = getContentResolver().openOutputStream(uriImagenRedimensionada);
                        imagenRedimensionada.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        outputStream.close();

                        perfilImg.setImageURI(uriImagenRedimensionada);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        });

        camaraResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            switch (result.getResultCode()) {
                case RESULT_CANCELED:
                    break;
                case RESULT_OK:
                    Intent data = result.getData();
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    pfpUri = GallerySaver.saveImageToGallery(imageBitmap);
                    perfilImg.setImageURI(pfpUri);
                    break;
            }
        });


        boolean editando = getIntent().getBooleanExtra("EDITANDO", false);
        String idPerfil = getIntent().getStringExtra("ID");

        Log.d("KEK", idPerfil);

        if (editando) {
            tModificar.setVisibility(View.VISIBLE);
            tInsertar.setVisibility(View.INVISIBLE);
            vm.getPerfil(idPerfil);
        } else {
            tModificar.setVisibility(View.INVISIBLE);
            tInsertar.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .clear(this.perfilImg);
            vm.clear();
            nombre.requestFocus();
        }

        vm.getPerfilLivedata().observe(this, perfil -> {
            nombre.setText(perfil.getNombre());
            nPartidas.setText(perfil.getPartidasJugadas());
            nGanadas.setText(perfil.getPartidasGanadas());
            nPuntuacion.setText(perfil.getMaxPuntuacion());
            if (editando) {
                currentPfpUrl = perfil.getPfpImg();
                Glide.with(this)
                        .load(currentPfpUrl)
                        .into(this.perfilImg);
            }
        });

        perfilImg.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Seleccionar imagen")
                    .setItems(new CharSequence[]{"Galería", "Cámara"}, (dialog, which) -> {
                        if (which == 0) {
                            checkGaleria();
                        } else if (which == 1) {
                            checkCamara();
                        }
                    })
                    .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss())
                    .show();
        });

        tBorrar.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(ActivityDetallePerfil.this);

            View confirmDialogView = getLayoutInflater().inflate(R.layout.confirm_dialog, null);
            builder.setView(confirmDialogView);

            TextView btnConfirmar = confirmDialogView.findViewById(R.id.tEliminarDialog);
            TextView btnCancelar = confirmDialogView.findViewById(R.id.tCancelarDialog);
            AlertDialog alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            btnConfirmar.setOnClickListener(v -> {
                vm.eliminarPerfil(idPerfil);
                alertDialog.dismiss();
                setResult(ActivityPerfiles.REFRESH);
                finish();
            });

            btnCancelar.setOnClickListener(v -> {
                // cancelar
                alertDialog.dismiss();
            });
            alertDialog.show();
        });

        tInsertar.setOnClickListener(view -> {
            perfilValido = true;
            if (nombre.getText().length() < 4 || nombre.getText().length() > 10) {
                Alert.alertError(ActivityDetallePerfil.this, getResources().getString(R.string.ErrorNombre));
                perfilValido = false;
            }
            if (perfilValido) {
                String newName = nombre.getText().toString();
                Perfil nuevoPerfil = new Perfil(email, newName, "0", "0", "0");
                nuevoPerfil.setPorcentajeGanadas("0");
                vm.insertarPerfil(nuevoPerfil, pfpUri);
                setResult(ActivityPerfiles.REFRESH);
                finish();
            }
        });

        tModificar.setOnClickListener(view -> {
            perfilValido = true;
            if (nombre.getText().length() < 4 || nombre.getText().length() > 10) {
                Alert.alertError(ActivityDetallePerfil.this, getResources().getString(R.string.ErrorNombre));
                perfilValido = false;
            }
            if (perfilValido) {
                String mNombre = String.valueOf(nombre.getText());
                String mPartidas = String.valueOf(nPartidas.getText());
                String mGanadas = String.valueOf(nGanadas.getText());
                String mPuntos = String.valueOf(nPuntuacion.getText());
                Perfil nPerfil = new Perfil(email, mNombre, mPartidas, mGanadas, mPuntos);
                nPerfil.setId(idPerfil);
                nPerfil.setPfpImg(currentPfpUrl);
                vm.modificarPerfil(idPerfil, nPerfil, pfpUri);
                setResult(ActivityPerfiles.REFRESH);
                finish();
            }
        });

        tCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(perfilImg)
                        .clear(perfilImg);
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDetallePerfil.this, Ranking.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDetallePerfil.this, PrincipalPartida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDetallePerfil.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            }
        });

        sMPerfilDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sMPerfilDetalle.isChecked()) {
                    Login.mp.start();
                    Login.music = true;
                } else {
                    Login.mp.pause();
                    Login.music = false;
                }
            }
        });
    }

    private void checkCamara() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
            iniciarCamara();
        } else if (false) {
            // dialog explicando porque necesitamos la camara
        } else {
            camaraLauncher.launch(Manifest.permission.CAMERA);
        }
    }

    private void iniciarCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camaraResult.launch(intent);
    }

    private void checkGaleria() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            iniciarGaleria();
        } else if (false) {
            // dialog explicando porque necesitamos su imagen
        } else {
            galleryLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    private void iniciarGaleria() {
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.putExtra(MediaStore.EXTRA_OUTPUT, pfpUri);
        galleryResult.launch(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Login.mp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Login.music) {
            Login.mp.start();
        } else {
            Login.mp.pause();
            sMPerfilDetalle.setChecked(false);
        }
    }
}