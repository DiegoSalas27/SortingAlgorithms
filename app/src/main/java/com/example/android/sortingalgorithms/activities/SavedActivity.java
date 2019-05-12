package com.example.android.sortingalgorithms.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.android.sortingalgorithms.Adapters.PartidasAdapter;
import com.example.android.sortingalgorithms.R;
import com.example.android.sortingalgorithms.models.Partida;
import com.example.android.sortingalgorithms.models.User;

import java.util.ArrayList;

public class SavedActivity extends Activity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    MediaPlayer myMedia2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);
        setTitle("Partidas guardadas");
        recyclerView = findViewById(R.id.albumsRecyclerView);
        myMedia2 = MediaPlayer.create(SavedActivity.this, R.raw.button3);
        progressBar = findViewById(R.id.homeProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        requestPartidas();
    }
    @Override
    public void onStart() {
        super.onStart();
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        this.getWindow().setLayout(width, height);
    }

    public void requestPartidas() {
        ArrayList<Partida> partidas;

        SharedPreferences preferencias =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        String correo = preferencias.getString("email", "por_defecto@email.com");

        User user;

        user = User.find(User.class, "email = ?", correo).get(0);

        partidas = (ArrayList<Partida>) user.getPartidas();

        Log.i("Partidas de ", user.getEmail() + ": " + partidas.toString());


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PartidasAdapter adapter = new PartidasAdapter(getBaseContext() ,partidas);
        recyclerView.setAdapter(adapter);

    }

    public void backtoMain(View view){
        myMedia2.start();
        finish();
    }
}
