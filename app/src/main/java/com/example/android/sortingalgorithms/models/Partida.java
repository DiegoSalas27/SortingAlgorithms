package com.example.android.sortingalgorithms.models;

import com.orm.SugarRecord;

public class Partida extends SugarRecord {
    Integer nivel;
    String algoritmo;
    Double puntaje;
    Integer iteracion;
    String numeros;
    User user;
    String fecha;

    public Partida(Integer nivel, String algoritmo, Double puntaje, Integer iteracion,
                   String numeros, User user, String fecha) {
        this.nivel = nivel;
        this.algoritmo = algoritmo;
        this.puntaje = puntaje;
        this.iteracion = iteracion;
        this.numeros = numeros;
        this.user = user;
        this.fecha = fecha;
    }

    public Partida() {
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(String algoritmo) {
        this.algoritmo = algoritmo;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }

    public Integer getIteracion() {
        return iteracion;
    }

    public void setIteracion(Integer iteracion) {
        this.iteracion = iteracion;
    }

    public String getNumeros() {
        return numeros;
    }

    public void setNumeros(String numeros) {
        this.numeros = numeros;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
