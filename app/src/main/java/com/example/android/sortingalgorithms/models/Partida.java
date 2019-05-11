package com.example.android.sortingalgorithms.models;

public class Partida {
    Integer nivel;
    String algoritmo;
    Double puntaje;
    Integer iteracion;
    String numeros;
    User user;

    public Partida(Integer nivel, String algoritmo, Double puntaje, Integer iteracion, String numeros, User user) {
        this.nivel = nivel;
        this.algoritmo = algoritmo;
        this.puntaje = puntaje;
        this.iteracion = iteracion;
        this.numeros = numeros;
        this.user = user;
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
}
