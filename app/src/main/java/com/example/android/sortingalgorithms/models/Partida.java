package com.example.android.sortingalgorithms.models;

import com.orm.SugarRecord;

public class Partida extends SugarRecord {
    Integer nivel;
    String algoritmo;
    Integer puntaje;
    Integer iteracion;
    String numeros;
    User user;
    Integer iteracionF;
    String fecha;
    long timeLeftInMilliseconds;

    public Partida(Integer nivel, String algoritmo, Integer puntaje, Integer iteracion,
                   String numeros, User user, String fecha, Integer iteracionF, long timeLeftInMilliseconds) {
        this.nivel = nivel;
        this.algoritmo = algoritmo;
        this.puntaje = puntaje;
        this.iteracion = iteracion;
        this.numeros = numeros;
        this.user = user;
        this.fecha = fecha;
        this.iteracionF = iteracionF;
        this.timeLeftInMilliseconds = timeLeftInMilliseconds;
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

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
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

    public Integer getIteracionF() {
        return iteracionF;
    }

    public void setIteracionF(Integer iteracionF) {
        this.iteracionF = iteracionF;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getTimeLeftInMilliseconds() {
        return timeLeftInMilliseconds;
    }

    public void setTimeLeftInMilliseconds(long timeLeftInMilliseconds) {
        this.timeLeftInMilliseconds = timeLeftInMilliseconds;
    }
}
