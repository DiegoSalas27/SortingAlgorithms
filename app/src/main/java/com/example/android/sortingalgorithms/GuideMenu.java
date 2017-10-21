package com.example.android.sortingalgorithms;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class GuideMenu extends Activity {

    Button btnOk;
    MediaPlayer myMedia2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_menu);
        btnOk = (Button) findViewById(R.id.btnOk);
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
