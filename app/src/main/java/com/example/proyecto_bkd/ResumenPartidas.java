package com.example.proyecto_bkd;

import java.util.ArrayList;
import java.util.Date;

public class ResumenPartidas {
    private String fecha,j1,j2,j3,j4;
    private int pto1,pto2,pto3,pto4;

    public ResumenPartidas(String fecha, String j1, String j2, int pto1, int pto2){
        this.fecha=fecha;
        this.j1=j1;
        this.j2=j2;
        this.pto1=pto1;
        this.pto2=pto2;
    }
    public ResumenPartidas(String fecha, String j1, String j2, String j3, int pto1, int pto2,int pto3){
        this.fecha=fecha;
        this.j1=j1;
        this.j2=j2;
        this.j3=j3;
        this.pto1=pto1;
        this.pto2=pto2;
        this.pto3=pto3;
    }
    public ResumenPartidas(String fecha, String j1, String j2,String j3,String j4, int pto1, int pto2,int pto3,int pto4){
        this.fecha=fecha;
        this.j1=j1;
        this.j2=j2;
        this.j3=j3;
        this.j4=j4;
        this.pto1=pto1;
        this.pto1=pto1;
        this.pto3=pto3;
        this.pto4=pto4;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getJ1() {
        return j1;
    }

    public void setJ1(String j1) {
        this.j1 = j1;
    }

    public String getJ2() {
        return j2;
    }

    public void setJ2(String j2) {
        this.j2 = j2;
    }

    public String getJ3() {
        return j3;
    }

    public void setJ3(String j3) {
        this.j3 = j3;
    }

    public String getJ4() {
        return j4;
    }

    public void setJ4(String j4) {
        this.j4 = j4;
    }

    public int getPto1() {
        return pto1;
    }

    public void setPto1(int pto1) {
        this.pto1 = pto1;
    }

    public int getPto2() {
        return pto2;
    }

    public void setPto2(int pto2) {
        this.pto2 = pto2;
    }

    public int getPto3() {
        return pto3;
    }

    public void setPto3(int pto3) {
        this.pto3 = pto3;
    }

    public int getPto4() {
        return pto4;
    }

    public void setPto4(int pto4) {
        this.pto4 = pto4;
    }

    public static ArrayList<ResumenPartidas> generador(){
        ArrayList<ResumenPartidas> resumen = new ArrayList<ResumenPartidas>();
        resumen.add(new ResumenPartidas("31-12-2022","Jorge","Miguel Angel",48,60));
        resumen.add(new ResumenPartidas("10-01-2023","Iñaki","Ana","Dario",99,126,115));
        return resumen;
    }
}
