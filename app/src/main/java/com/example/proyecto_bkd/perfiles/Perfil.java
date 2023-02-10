package com.example.proyecto_bkd.perfiles;

import java.io.Serializable;
import java.util.ArrayList;

public class Perfil implements Serializable {

    private static String [] perfiles_genericos = {
            "Jorge", "Miguel Ángel", "Darío"," Kayler", "Brandon",
            "Lara", "Robert", "Ana"," Stefi", "Cheng"
    };

    private String nombre;

    public int puntuacionGeneral;

    //Aquí irían fotos

    public Perfil(String nombre, int puntuacionGeneral){
        this.nombre = nombre;
        this.puntuacionGeneral = puntuacionGeneral;
    }

    public Perfil() {}



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntuacionGeneral() {
        return puntuacionGeneral;
    }

    public void setPuntuacionGeneral(int puntuacionGeneral) {
        this.puntuacionGeneral = puntuacionGeneral;
    }

    public static Perfil[] generarPerfiles(){
        Perfil [] listaPerfiles= new Perfil[perfiles_genericos.length];
        int dummyPoints = 1000;
        for (int i = 0; i < perfiles_genericos.length; i++) {
            listaPerfiles[i] = new Perfil(perfiles_genericos[i],dummyPoints);
            dummyPoints-=25;
        }
        return  listaPerfiles;
    }
}
