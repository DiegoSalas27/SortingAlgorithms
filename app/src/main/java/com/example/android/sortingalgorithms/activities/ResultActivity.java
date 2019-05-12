package com.example.android.sortingalgorithms.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.sortingalgorithms.R;
import com.example.android.sortingalgorithms.models.Resultado;
import com.example.android.sortingalgorithms.models.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultActivity extends Activity {

    Button btnSaveResult, goToMain;
    MediaPlayer myMedia, mediaCrowd;
    TextView tvPuntaje, tvNivelesAl, tvTiempoLo, tvRecomendacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        myMedia = MediaPlayer.create(ResultActivity.this, R.raw.button3);
        mediaCrowd = MediaPlayer.create(ResultActivity.this, R.raw.finaboo);
        btnSaveResult = findViewById(R.id.btnSaveResult);
        goToMain = findViewById(R.id.goToMain);
        if (getIntent().getExtras() != null) {
            showResults(getIntent().getExtras().getString("nivel"),
                    getIntent().getExtras().getString("puntaje"),
                    getIntent().getExtras().getString("timeLeftInMilliseconds"));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        this.getWindow().setLayout(width, height);
    }

    public void showResults(String nivel, String puntaje, String timeLeftInMilliseconds) {

        tvPuntaje = findViewById(R.id.tvPuntaje);
        tvNivelesAl = findViewById(R.id.tvNivelesAl);
        tvTiempoLo = findViewById(R.id.tvTiempoLo);
        tvRecomendacion = findViewById(R.id.tvRecomendacion);

        int minutes = Integer.valueOf(timeLeftInMilliseconds) / 60000;
        int seconds = Integer.valueOf(timeLeftInMilliseconds) % 60000 / 1000;

        String timeleftText;
        timeleftText = "" + minutes;
        timeleftText += ":";
        if (seconds < 10) timeleftText += "0";
        timeleftText += seconds;

        tvTiempoLo.setText(timeleftText);
        tvPuntaje.setText(puntaje);
        tvNivelesAl.setText(nivel);

        if (Integer.valueOf(puntaje) > 30) {
            mediaCrowd = MediaPlayer.create(ResultActivity.this, R.raw.finalapplause);
            tvRecomendacion.setText("Has logadro en: " + tvTiempoLo.getText() + "un puntaje de: " + tvPuntaje.getText() +
                    "Es un excelente resultado.");
            mediaCrowd.start();

        } else {
            mediaCrowd = MediaPlayer.create(ResultActivity.this, R.raw.finaboo);
            tvRecomendacion.setText("Has logadro en: " + tvTiempoLo.getText() + " un puntaje de: " + tvPuntaje.getText() +
                    " Es un resultado por debajo del promedio.");
            mediaCrowd.start();
        }
    }

    public void goToMainBoard(View view){
        myMedia.start();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void saveResult(View view) {
        SharedPreferences preferencias =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        String correo = preferencias.getString("email", "por_defecto@email.com");

        User user;

        user = User.find(User.class, "email = ?", correo).get(0);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        String currentDateandTime = sdf.format(new Date());

        Resultado resultado = new Resultado(Integer.valueOf(tvPuntaje.getText().toString()),
                Integer.valueOf(tvNivelesAl.getText().toString()), tvTiempoLo.getText().toString(), user, currentDateandTime);

        resultado.save();
    }
}
