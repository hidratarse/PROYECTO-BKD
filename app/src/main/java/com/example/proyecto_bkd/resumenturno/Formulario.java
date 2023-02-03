package com.example.proyecto_bkd.resumenturno;

import java.util.ArrayList;

public class Formulario  {
    int num;

    public Formulario(int n){
        this.num = n;
    }

    public static ArrayList<Formulario> generarFormularios(){
        ArrayList<Formulario> formularios = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            formularios.add(new Formulario(i));
        }
        return formularios;
    }
}
