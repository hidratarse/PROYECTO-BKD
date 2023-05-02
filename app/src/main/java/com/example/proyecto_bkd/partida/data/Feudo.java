package com.example.proyecto_bkd.partida.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Feudo implements Serializable {
    private ArrayList<String> Recursos = new ArrayList<String>();
    private int puntos;
    private int torres;

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

    public String toString(){
        String sTotal = "";
        for (String s: getRecursos()){
            sTotal+=s;
        }
        sTotal += " " + getTorres() + " " +getPuntos();
        return sTotal;
    }
}
