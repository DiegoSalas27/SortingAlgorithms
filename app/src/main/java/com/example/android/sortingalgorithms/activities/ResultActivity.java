package com.example.android.sortingalgorithms.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.sortingalgorithms.R;
import com.example.android.sortingalgorithms.models.Resultado;
import com.example.android.sortingalgorithms.models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ResultActivity extends Activity {

    Button btnSaveResult, goToMain;
    MediaPlayer myMedia, mediaCrowd;
    TextView tvPuntaje, tvNivelesAl, tvTiempoLo, tvRecomendacion, tvPuntajeMejor;
    ImageView imgCrowd;
    User user;
    int max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        myMedia = MediaPlayer.create(ResultActivity.this, R.raw.button3);
        mediaCrowd = MediaPlayer.create(ResultActivity.this, R.raw.finaboo);
        imgCrowd = findViewById(R.id.imgCrowd);
        btnSaveResult = findViewById(R.id.btnSaveResult);
        goToMain = findViewById(R.id.goToMain);

        getUsuario();

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

    public void getUsuario() {
        ArrayList<Resultado> resultados;

        SharedPreferences preferencias =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        String correo = preferencias.getString("email", "por_defecto@email.com");

        user = User.find(User.class, "email = ?", correo).get(0);

        String puntaje = preferencias.getString("MejorPuntaje", "0");

        if (puntaje == "0") {
            max = 0;
        } else {
            max = Integer.valueOf(puntaje);
        }
    }

    public void showResults(String nivel, String puntaje, String timeLeftInMilliseconds) {

        tvPuntaje = findViewById(R.id.tvPuntaje);
        tvNivelesAl = findViewById(R.id.tvNivelesAl);
        tvTiempoLo = findViewById(R.id.tvTiempoLo);
        tvRecomendacion = findViewById(R.id.tvRecomendacion);
        tvPuntajeMejor = findViewById(R.id.tvPuntajeMejor);

        int minutes = Integer.valueOf(timeLeftInMilliseconds) / 60000;
        int seconds = Integer.valueOf(timeLeftInMilliseconds) % 60000 / 1000;

        String timeleftText;
        timeleftText = "" + minutes;
        timeleftText += ":";
        if (seconds < 10) timeleftText += "0";
        timeleftText += seconds;

       // tvTiempoLo.setText(timeleftText);
        tvPuntajeMejor.setText(String.valueOf(max));
        tvTiempoLo.setText("2:00");
        tvPuntaje.setText(puntaje);
        tvNivelesAl.setText(nivel);

        if (Integer.valueOf(puntaje) > 30) {
            if (Integer.valueOf(tvPuntaje.getText().toString()) > Integer.valueOf(tvPuntajeMejor.getText().toString())
            && max != 0) {
                tvRecomendacion.setText("Has logadro en: " + "2:00" + " un puntaje de: " + tvPuntaje.getText() +
                        " Es un excelente resultado. Has superado tu record!");
            } else if (max == 0){
                tvRecomendacion.setText("Has logadro en: " + "2:00" + " un puntaje de: " + tvPuntaje.getText() +
                        " Es un excelente resultado.");
            } else {
                tvRecomendacion.setText("Has logadro en: " + "2:00" + " un puntaje de: " + tvPuntaje.getText() +
                        " Es un excelente resultado. Estás por debajo de tu record!");
            }
            mediaCrowd = MediaPlayer.create(ResultActivity.this, R.raw.finalapplause);
            imgCrowd.setImageResource(R.drawable.cheeringcrowd);
            mediaCrowd.start();
        } else {
            if (Integer.valueOf(tvPuntaje.getText().toString()) > Integer.valueOf(tvPuntajeMejor.getText().toString())
            && max != 0) {
                tvRecomendacion.setText("Has logadro en: " + "2:00" + " un puntaje de: " + tvPuntaje.getText() +
                        " Es un resultado por debajo del promedio pero por sobre tu record. ");
            } else if (max == 0){
                tvRecomendacion.setText("Has logadro en: " + "2:00" + " un puntaje de: " + tvPuntaje.getText() +
                        " Es un resultado por debajo del promedio.");
            } else {
                tvRecomendacion.setText("Has logadro en: " + "2:00" + " un puntaje de: " + tvPuntaje.getText() +
                        " Es un resultado por debajo del promedio. Estás igual o por debajo de tu record");
            }
            mediaCrowd = MediaPlayer.create(ResultActivity.this, R.raw.finaboo);
            imgCrowd.setImageResource(R.drawable.boocrowd);
            mediaCrowd.start();
        }
    }

    public void goToMainBoard(View view){
        myMedia.start();
        mediaCrowd.stop();
        mediaCrowd.release();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        myMedia.start();
        mediaCrowd.stop();
        mediaCrowd.release();
        finish();
    }

    public void saveResult(View view) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        String currentDateandTime = sdf.format(new Date());

        Resultado resultado = new Resultado(Integer.valueOf(tvPuntaje.getText().toString()),
                Integer.valueOf(tvNivelesAl.getText().toString()), tvTiempoLo.getText().toString(), user, currentDateandTime);

        Log.d("RESULTADO", resultado.getPuntaje().toString());

        SharedPreferences preferencias =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        String puntaje = preferencias.getString("MejorPuntaje", "0");

        if (Integer.valueOf(puntaje) < resultado.getPuntaje()) {
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString("MejorPuntaje", resultado.getPuntaje().toString());
            editor.commit();
        }

        Toast.makeText(getApplicationContext(),"Resultado guardado!", Toast.LENGTH_SHORT).show();
    }
}
