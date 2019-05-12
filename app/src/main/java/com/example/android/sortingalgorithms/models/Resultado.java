package com.example.android.sortingalgorithms.models;

import com.orm.SugarRecord;

public class Resultado extends SugarRecord {
    Integer puntaje;
    Integer nivel;
    String tiempo;
    User user;
    String fecha;

    public Resultado(Integer puntaje, Integer nivel, String tiempo, User user, String fecha) {
        this.puntaje = puntaje;
        this.nivel = nivel;
        this.tiempo = tiempo;
        this.fecha = fecha;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
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
