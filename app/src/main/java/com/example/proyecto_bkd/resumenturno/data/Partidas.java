package com.example.proyecto_bkd.resumenturno.data;

public class Partidas {
    private String idPartida, email, fecha;
    private Jugador j1, j2, j3,j4;

    public Partidas(String email, String fecha, Jugador j1, Jugador j2) {
        this.email = email;
        this.fecha = fecha;
        this.j1 = j1;
        this.j2 = j2;
    }

    public Partidas(String email, String fecha, Jugador j1, Jugador j2, Jugador j3) {
        this.email = email;
        this.fecha = fecha;
        this.j1 = j1;
        this.j2 = j2;
        this.j3 = j3;
    }

    public Partidas(String email, String fecha, Jugador j1, Jugador j2, Jugador j3, Jugador j4) {
        this.email = email;
        this.fecha = fecha;
        this.j1 = j1;
        this.j2 = j2;
        this.j3 = j3;
        this.j4 = j4;
    }

    public String getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(String idPartida) {
        this.idPartida = idPartida;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Jugador getJ1() {
        return j1;
    }

    public void setJ1(Jugador j1) {
        this.j1 = j1;
    }

    public Jugador getJ2() {
        return j2;
    }

    public void setJ2(Jugador j2) {
        this.j2 = j2;
    }

    public Jugador getJ3() {
        return j3;
    }

    public void setJ3(Jugador j3) {
        this.j3 = j3;
    }

    public Jugador getJ4() {
        return j4;
    }

    public void setJ4(Jugador j4) {
        this.j4 = j4;
    }
}
