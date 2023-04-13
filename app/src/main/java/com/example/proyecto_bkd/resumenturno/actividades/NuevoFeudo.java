package com.example.proyecto_bkd.resumenturno.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.proyecto_bkd.Login;
import com.example.proyecto_bkd.R;
import com.example.proyecto_bkd.partida.Partida;
import com.example.proyecto_bkd.perfiles.actividades.ActivityPerfiles;
import com.example.proyecto_bkd.ranking.activity_ranking2;
import com.example.proyecto_bkd.resumenturno.Feudo;

import java.io.Serializable;
import java.util.ArrayList;

public class NuevoFeudo extends AppCompatActivity implements Serializable {

    ImageButton mas, menos, bImgPartidas,bImgPerfiles,bImgRanking;
    TextView torres, tCancelar, tAnadir,tPuntosFeudo;
    ImageView madera,pez,zanahoria,polvo,seta,plata,oro,cobre,diamante,perla;
    Switch sMNuevoFeudo;
    public static final int ACTUALIZAR_ADAPTER=1;

    private enum Recursos{madera, pez, zanahoria,polvo, seta, plata, oro, cobre, diamante, perla};
    private int recursosCont=0;
    private int puntuacionFeudo=0;

    ArrayList<String> listaRecursos = new ArrayList<String>();

    public class Seleccionrecursos implements View.OnClickListener{
        ImageView fotoRecurso;
        Enum recursos;

        public Seleccionrecursos(ImageView fotoRecurso,Enum recursos){
            this.fotoRecurso = fotoRecurso;
            this.recursos = recursos;
        }

        @Override
        public void onClick(View view) {
            Recursos seleccionado = Recursos.valueOf(recursos.name());

            switch (seleccionado){
                case madera:
                    madera.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    if(!listaRecursos.contains("madera")){
                        listaRecursos.add("madera");
                        recursosCont+=recursosCont;
                    }else{
                        listaRecursos.remove("madera");
                        madera.setBackground(getResources().getDrawable(R.drawable.vacio));
                        recursosCont-=recursosCont;
                        tPuntosFeudo.setText(Integer.parseInt(torres.getText().toString())*recursosCont+"");
                    }
                    contarPuntos();
                    break;
                case zanahoria:
                    zanahoria.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    if(!listaRecursos.contains("zanahoria")){
                        listaRecursos.add("zanahoria");
                        recursosCont+=recursosCont;
                    }else{
                        listaRecursos.remove("zanahoria");
                        zanahoria.setBackground(getResources().getDrawable(R.drawable.vacio));
                        recursosCont-=recursosCont;
                    }
                    contarPuntos();
                    break;
                case pez:
                    pez.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    if(!listaRecursos.contains("pez")){
                        listaRecursos.add("pez");
                        recursosCont+=recursosCont;
                    }else{
                        listaRecursos.remove("pez");
                        pez.setBackground(getResources().getDrawable(R.drawable.vacio));
                        recursosCont-=recursosCont;
                    }
                    contarPuntos();
                    break;
                case oro:
                    oro.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    if(!listaRecursos.contains("oro")){
                        listaRecursos.add("oro");
                        recursosCont+=recursosCont;
                    }else{
                        listaRecursos.remove("oro");
                        oro.setBackground(getResources().getDrawable(R.drawable.vacio));
                        recursosCont-=recursosCont;
                    }
                    contarPuntos();
                    break;
                case plata:
                    plata.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    if(!listaRecursos.contains("plata")){
                        listaRecursos.add("plata");
                        recursosCont+=recursosCont;
                    }else{
                        listaRecursos.remove("plata");
                        plata.setBackground(getResources().getDrawable(R.drawable.vacio));
                        recursosCont-=recursosCont;
                    }
                    contarPuntos();
                    break;
                case cobre:
                    cobre.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    if(!listaRecursos.contains("cobre")){
                        listaRecursos.add("cobre");
                        recursosCont+=recursosCont;
                    }else{
                        listaRecursos.remove("cobre");
                        cobre.setBackground(getResources().getDrawable(R.drawable.vacio));
                        recursosCont-=recursosCont;
                    }
                    contarPuntos();
                    break;
                case diamante:
                    diamante.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    if(!listaRecursos.contains("diamante")){
                        listaRecursos.add("diamante");
                        recursosCont+=recursosCont;
                    }else{
                        listaRecursos.remove("diamante");
                        diamante.setBackground(getResources().getDrawable(R.drawable.vacio));
                        recursosCont-=recursosCont;
                    }
                    contarPuntos();
                    break;
                case polvo:
                    polvo.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    if(!listaRecursos.contains("polvo")){
                        listaRecursos.add("polvo");
                        recursosCont+=recursosCont;
                    }else{
                        listaRecursos.remove("polvo");
                        polvo.setBackground(getResources().getDrawable(R.drawable.vacio));
                        recursosCont-=recursosCont;
                    }
                    contarPuntos();
                    break;
                case seta:
                    seta.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    if(!listaRecursos.contains("seta")){
                        listaRecursos.add("seta");
                        recursosCont+=recursosCont;
                    }else{
                        listaRecursos.remove("seta");
                        seta.setBackground(getResources().getDrawable(R.drawable.vacio));
                        recursosCont-=recursosCont;
                    }
                    contarPuntos();
                    break;
                case perla:
                    perla.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                    if(!listaRecursos.contains("perla")){
                        listaRecursos.add("perla");
                        recursosCont+=recursosCont;
                    }else{
                        listaRecursos.remove("perla");
                        perla.setBackground(getResources().getDrawable(R.drawable.vacio));
                        recursosCont-=recursosCont;
                    }
                    contarPuntos();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_feudo);
        tPuntosFeudo = findViewById(R.id.tPuntosFeudo);
        mas=findViewById(R.id.imgMas);
        menos=findViewById(R.id.imgMenos);
        torres=findViewById(R.id.tNum);
        tCancelar=findViewById(R.id.tCancelar);
        tAnadir=findViewById(R.id.tAnadir);
        madera=findViewById(R.id.imgMadera);
        pez=findViewById(R.id.imgPez);
        zanahoria=findViewById(R.id.imgZanahoria);
        polvo=findViewById(R.id.imgPolvo);
        seta=findViewById(R.id.imgSeta);
        plata=findViewById(R.id.imgPlata);
        oro=findViewById(R.id.imgOro);
        cobre=findViewById(R.id.imgCobre);
        diamante=findViewById(R.id.imgDiamante);
        perla=findViewById(R.id.imgPerla);
        sMNuevoFeudo = findViewById(R.id.sMNuevoFeudo);
        bImgPartidas=findViewById(R.id.bImgPartidas);
        bImgPerfiles=findViewById(R.id.bImgPerfiles);
        bImgRanking=findViewById(R.id.bImgRanking);

        Seleccionrecursos manejadorMadera = new Seleccionrecursos(madera, Recursos.madera);
        Seleccionrecursos manejadorZanahoria = new Seleccionrecursos(zanahoria, Recursos.zanahoria);
        Seleccionrecursos manejadorPez = new Seleccionrecursos(pez, Recursos.pez);
        Seleccionrecursos manejadorPolvo = new Seleccionrecursos(polvo, Recursos.polvo);
        Seleccionrecursos manejadorSeta = new Seleccionrecursos(seta, Recursos.seta);
        Seleccionrecursos manejadorOro = new Seleccionrecursos(oro, Recursos.oro);
        Seleccionrecursos manejadorPlata = new Seleccionrecursos(plata, Recursos.plata);
        Seleccionrecursos manejadorCobre = new Seleccionrecursos(cobre, Recursos.cobre);
        Seleccionrecursos manejadorDiamante = new Seleccionrecursos(diamante, Recursos.diamante);
        Seleccionrecursos manejadorPerla = new Seleccionrecursos(perla, Recursos.perla);

        madera.setOnClickListener(manejadorMadera);
        zanahoria.setOnClickListener(manejadorZanahoria);
        pez.setOnClickListener(manejadorPez);
        polvo.setOnClickListener(manejadorPolvo);
        seta.setOnClickListener(manejadorSeta);
        oro.setOnClickListener(manejadorOro);
        plata.setOnClickListener(manejadorPlata);
        cobre.setOnClickListener(manejadorCobre);
        diamante.setOnClickListener(manejadorDiamante);
        perla.setOnClickListener(manejadorPerla);

        sMNuevoFeudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMNuevoFeudo.isChecked()){
                    Login.mp.start();
                }else{
                    Login.mp.pause();
                }
            }
        });

        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevoFeudo.this, activity_ranking2.class);
                startActivity(intent);
                finish();
            }
        });

        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevoFeudo.this, Partida.class);
                startActivity(intent);
                finish();
            }
        });
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevoFeudo.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            }
        });

        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                torres.setText((Integer.parseInt(torres.getText().toString())+1)+"");
                contarPuntos();
            }
        });
        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(torres.getText().toString())==0){
                    torres.setText("0");
                }else{
                    torres.setText((Integer.parseInt(torres.getText().toString())-1)+"");
                }
                contarPuntos();
            }
        });
        tCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(NuevoFeudo.this, activity_ResumenTurno.class);
                //startActivity(intent);
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        tAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Feudo f = new Feudo(listaRecursos,Integer.parseInt(torres.getText().toString()),puntuacionFeudo);
                Log.d("listado",f.getRecursos().toString()+" "+f.getTorres()+" "+f.getPuntos());
                //Intent intent = new Intent(NuevoFeudo.this, activity_ResumenTurno.class);
                Intent intent = new Intent();
                intent.putExtra("enviar", f);
                setResult(ACTUALIZAR_ADAPTER,intent);
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
    private void contarPuntos(){
        puntuacionFeudo=listaRecursos.size()*Integer.parseInt(torres.getText().toString());
        Log.d("PUNTUACION", String.valueOf(puntuacionFeudo));
        tPuntosFeudo.setText(String.valueOf(puntuacionFeudo));
    }

}