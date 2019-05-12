package com.example.android.sortingalgorithms.activities;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.sortingalgorithms.R;

public class GuideMenu extends Activity {

    Button btnOk;
    MediaPlayer myMedia2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_menu);
        btnOk = findViewById(R.id.btnOk);
        myMedia2 = MediaPlayer.create(GuideMenu.this, R.raw.button3);
    }
    @Override
    public void onStart() {
        super.onStart();
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        this.getWindow().setLayout(width, height);
    }
    public void backtoMain(View view){
        myMedia2.start();
        finish();
    }
}
