package com.example.proyecto_bkd.resumenturno.data;

public class Jugador {
    String idJugador, nomJugador, color;
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

    public String getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(String idJugador) {
        this.idJugador = idJugador;
    }
}
