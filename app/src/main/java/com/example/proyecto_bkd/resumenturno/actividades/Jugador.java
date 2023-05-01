package com.example.proyecto_bkd.resumenturno.actividades;

public class Jugador {
    String nomJugador, color;
    int contColor, puntos, posicion;

    public Jugador(String nomJugador, String color, int contColor) {
        this.nomJugador = nomJugador;
        this.color = color;
        this.contColor = contColor;
    }

    public String getNomJugador() {
        return nomJugador;
    }

    public void setNomJugador(String nomJugador) {
        this.nomJugador = nomJugador;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getContColor() {
        return contColor;
    }

    public void setContColor(int contColor) {
        this.contColor = contColor;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
