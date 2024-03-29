package com.example.proyecto_bkd.perfiles.data;

import java.io.Serializable;

public class Perfil implements Serializable {

    private String id;

    private String email;

    private String nombre;

    private String pfpImg;

    private String partidasJugadas;

    private String maxPuntuacion;

    private String partidasGanadas;

    private String porcentajeGanadas;

    public Perfil() {
    }

    public Perfil(String email, String nombre, String partidasJugadas, String maxPuntuacion, String partidasGanadas, String porcentajeGanadas) {
        this.email = email;
        this.nombre = nombre;
        this.partidasJugadas = partidasJugadas;
        this.maxPuntuacion = maxPuntuacion;
        this.partidasGanadas = partidasGanadas;
        this.porcentajeGanadas = porcentajeGanadas;
    }

    public Perfil(String nombre, String partidasJugadas, String maxPuntuacion, String partidasGanadas, String porcentajeGanadas) {
        this.nombre = nombre;
        this.partidasJugadas = partidasJugadas;
        this.maxPuntuacion = maxPuntuacion;
        this.partidasGanadas = partidasGanadas;
        this.porcentajeGanadas = porcentajeGanadas;
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

    public String getPartidasJugadas() {
        return partidasJugadas;
    }

    public String getMaxPuntuacion() {
        return maxPuntuacion;
    }

    public String getPartidasGanadas() {
        return partidasGanadas;
    }

    public String getPfpImg() {return pfpImg;}

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPorcentajeGanadas() {
        return porcentajeGanadas;
    }

    public void setPorcentajeGanadas(String porcentajeGanadas) {
        this.porcentajeGanadas = porcentajeGanadas;
    }

    public void setPartidasJugadas(String partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

    public void setMaxPuntuacion(String maxPuntuacion) {
        this.maxPuntuacion = maxPuntuacion;
    }

    public void setPartidasGanadas(String partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public void setPfpImg(String pfpImg) {
        this.pfpImg = pfpImg;
    }
}
