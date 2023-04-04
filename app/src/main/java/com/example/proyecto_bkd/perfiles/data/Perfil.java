package com.example.proyecto_bkd.perfiles.data;

import java.io.Serializable;

public class Perfil implements Serializable {

    private String id;

    private String email;

    private String nombre;

    //foto de perfil, en URI?

    private int partidasJugadas;

    private int maxPuntuacion;

    private int partidasGanadas;

    public Perfil() {
    }

    public Perfil(String email, String nombre, int partidasJugadas, int maxPuntuacion, int partidasGanadas) {
        this.email = email;
        this.nombre = nombre;
        this.partidasJugadas = partidasJugadas;
        this.maxPuntuacion = maxPuntuacion;
        this.partidasGanadas = partidasGanadas;
    }

    public String getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public int getMaxPuntuacion() {
        return maxPuntuacion;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

    public void setMaxPuntuacion(int maxPuntuacion) {
        this.maxPuntuacion = maxPuntuacion;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }
}
