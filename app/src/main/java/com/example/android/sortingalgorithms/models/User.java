package com.example.android.sortingalgorithms.models;

import com.orm.SugarRecord;

import java.util.List;

public class User extends SugarRecord {
    String email;
    String password;
    public List<Partida> getPartidas() {
        return Partida.find(Partida.class, "user = ?", String.valueOf(this.getId()));
    }

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
