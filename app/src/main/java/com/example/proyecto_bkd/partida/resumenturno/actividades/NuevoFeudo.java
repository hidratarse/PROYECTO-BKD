package com.example.proyecto_bkd.partida.resumenturno.actividades;

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
import com.example.proyecto_bkd.SplashScreen;
import com.example.proyecto_bkd.partida.actividades.PrincipalPartida;
import com.example.proyecto_bkd.partida.data.Feudo;
import com.example.proyecto_bkd.perfiles.actividades.ActivityPerfiles;
import com.example.proyecto_bkd.ranking.Ranking;

import java.io.Serializable;
import java.util.ArrayList;

public class NuevoFeudo extends AppCompatActivity implements Serializable {

    //Elementos del layout
    ImageButton mas, menos, bImgPartidas,bImgPerfiles,bImgRanking;
    TextView torres, tCancelar, tAnadir,tPuntosFeudo;
    ImageView madera,pez,zanahoria,polvo,seta,plata,oro,cobre,diamante,perla;
    Switch sMNuevoFeudo;
    //Atributo para el ActivityResultLauncher
    public static final int ACTUALIZAR_ADAPTER=1;
    public static final int ACTUALIZAR_FEUDO=2;
    int posicionFeudo;
    private int puntuacionFeudo=0;
    int restaPuntos;
    //Atributos para los recursos
    private enum Recursos{madera, pez, zanahoria,polvo, seta, plata, oro, cobre, diamante, perla};
    private int recursosCont=0;
    ArrayList<String> listaRecursos = new ArrayList<String>();
    Feudo feudoEditado;

    public class Seleccionrecursos implements View.OnClickListener{
        ImageView fotoRecurso;
        Enum recursos;

        public Seleccionrecursos(ImageView fotoRecurso,Enum recursos){
            this.fotoRecurso = fotoRecurso;
            this.recursos = recursos;
        }
        //Se define el método onClick para añadir o quitar recursos de la lista
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

        //Se crean los manejadores de los recursos
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

        //Se llama al método onclick de los recursos con el manejador como parámetro
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

        //Método para controlar la música
        sMNuevoFeudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sMNuevoFeudo.isChecked()){
                    SplashScreen.mp.start();
                    SplashScreen.music =true;
                }else {
                    SplashScreen.mp.pause();
                    SplashScreen.music = false;
                }
            }
        });

        //Metodo para navegar de actividad entre los estandartes
        bImgRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevoFeudo.this, Ranking.class);
                startActivity(intent);
                finish();
            }
        });

        //Metodo para navegar de actividad entre los estandartes
        bImgPartidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevoFeudo.this, PrincipalPartida.class);
                startActivity(intent);
                finish();
            }
        });

        //Metodo para navegar de actividad entre los estandartes
        bImgPerfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevoFeudo.this, ActivityPerfiles.class);
                startActivity(intent);
                finish();
            }
        });

        //Método para aumentar el número de torres
        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                torres.setText((Integer.parseInt(torres.getText().toString())+1)+"");
                contarPuntos();
            }
        });
        //Método para reducir el número de torres, no puede bajar de 0
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
        try{
            feudoEditado = (Feudo) getIntent().getSerializableExtra("editarFeudo");
            posicionFeudo = getIntent().getIntExtra("posicion",0);
            listaRecursos=feudoEditado.getRecursos();
            puntuacionFeudo=feudoEditado.getPuntos();
            restaPuntos=feudoEditado.getPuntos();
            tPuntosFeudo.setText(String.valueOf(puntuacionFeudo));
            torres.setText(String.valueOf(feudoEditado.getTorres()));

            for (int i = 0; i < listaRecursos.size(); i++) {
                switch (listaRecursos.get(i)){
                    case "madera":
                        madera.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                        break;
                    case "zanahoria":
                        zanahoria.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                        break;
                    case "pez":
                        pez.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                        break;
                    case "oro":
                        oro.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                        break;
                    case "plata":
                        plata.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                        break;
                    case "cobre":
                        cobre.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                        break;
                    case "diamante":
                        diamante.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                        break;
                    case "polvo":
                        polvo.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                        break;
                    case "seta":
                        seta.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                        break;
                    case "perla":
                        perla.setBackground(getResources().getDrawable(R.drawable.borde_recurso));
                        break;
                }
            }
        }catch (Exception ex){
            Log.d("NO EDITADO","");
        }

        //Al pulsar cancelar se llama ActivityResultLauncher
        tCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        //Se envia la información y a la actividad para que se actualice el adapter
        tAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if(feudoEditado!=null){
                    feudoEditado.setPuntos(puntuacionFeudo);
                    feudoEditado.setTorres(Integer.parseInt(torres.getText().toString()));
                    feudoEditado.setRecursos(listaRecursos);
                    intent.putExtra("enviar",feudoEditado);
                    intent.putExtra("posicion",posicionFeudo);
                    intent.putExtra("restar",restaPuntos);
                    setResult(ACTUALIZAR_FEUDO,intent);
                }else{
                    Feudo f = new Feudo(listaRecursos,Integer.parseInt(torres.getText().toString()),puntuacionFeudo);
                    intent.putExtra("enviar", f);
                    setResult(ACTUALIZAR_ADAPTER,intent);
                }

                finish();
            }
        });
    }

    //Se para la música
    @Override
    protected void onPause() {
        super.onPause();
        SplashScreen.mp.pause();
    }

    //Se reanuda la música
    @Override
    protected void onResume() {
        super.onResume();
        if(SplashScreen.music){
            SplashScreen.mp.start();
        }else{
            SplashScreen.mp.pause();
            sMNuevoFeudo.setChecked(false);
        }
    }
    //metodo que multiplica los puntos de las torres por el número de recursos y actualiza elemento en layout
    private void contarPuntos(){
        puntuacionFeudo=listaRecursos.size()*Integer.parseInt(torres.getText().toString());
        tPuntosFeudo.setText(String.valueOf(puntuacionFeudo));
    }

}