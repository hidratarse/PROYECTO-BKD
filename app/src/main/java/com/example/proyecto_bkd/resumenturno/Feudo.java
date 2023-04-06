package com.example.proyecto_bkd.resumenturno;

import java.io.Serializable;
import java.util.ArrayList;

public class Feudo implements Serializable {
    private static ArrayList<String> Recursos = new ArrayList<String>();
    private static int puntos;
    private static int torres;
    public Feudo(){}
    public Feudo(ArrayList<String> recursos, int torres, int puntos) {
        Recursos = recursos;
        this.puntos = puntos;
        this.torres = torres;
    }

    public ArrayList<String> getRecursos() {
        return Recursos;
    }

    public void setRecursos(ArrayList<String> recursos) {
        Recursos = recursos;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getTorres() {
        return torres;
    }

    public void setTorres(int torres) {
        this.torres = torres;
    }
}
