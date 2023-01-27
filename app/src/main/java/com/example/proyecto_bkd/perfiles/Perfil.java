package com.example.proyecto_bkd.perfiles;

import java.util.ArrayList;

public class Perfil {

    private static String [] perfiles_genericos = {
            "Jorge", "Miguel Ángel", "Darío"," Kayler", "Brandon"
    };

    private String nombre;
    //Aquí irían fotos

    public Perfil(String nombre){
        this.nombre = nombre;
    }

    public Perfil() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static Perfil[] generarPerfiles(){
        Perfil [] listaPerfiles= new Perfil[perfiles_genericos.length];
        for (int i = 0; i < perfiles_genericos.length; i++) {
            listaPerfiles[i] = new Perfil(perfiles_genericos[i]);
        }
        return  listaPerfiles;
    }
}
