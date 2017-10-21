package com.example.android.sortingalgorithms;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    LinearLayout loTarget1, loTarget2, loTarget3, loTarget4, loTarget5, loFatherTarget, loFatherDrag;
    RelativeLayout roBlueSquare1, roBlueSquare2, roBlueSquare3, roBlueSquare4, roBlueSquare5;
    ImageView imgBlueSquare, imgBlueSquare2, imgBlueSquare3, imgBlueSquare4, imgBlueSquare5,
            imgGetter1, imgGetter2, imgGetter3, imgGetter4, imgGetter5, imgClaps, imgBackground, imgBackgroundMain;
    TextView tvAlgorithmName, tvBlueQuareText, tvBlueQuareText2, tvBlueQuareText3, tvBlueQuareText4, tvBlueQuareText5, tvIteration,
    tvSortResult, tvYourResult;
    RelativeLayout gameRelativeLayout;
    MediaPlayer myMedia, myMedia2, myMedia3;
    Button startButton, btnSee, btnGuide, btnBack;
    String sortAlgorithm;
    boolean result, once = false;
    public static int  ite1;
    public static String algorithmType;

    int arrInt[] = new int[5];
    int yourArray[] = new int[5];
    int i = 0, randomInt = 0;

    AlgoritmosDeOrdenamiento algoritmito = new AlgoritmosDeOrdenamiento();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myMedia2 = MediaPlayer.create(MainActivity.this, R.raw.button3);
        myMedia3 = MediaPlayer.create(MainActivity.this, R.raw.button1);
        Datos.setPasa(false);
        ite1 = 0;
        inicializarDatos();
    }

    public void inicializarDatos(){

        imgBlueSquare = (ImageView) findViewById(R.id.imgBlueQuare);
        imgBlueSquare2 = (ImageView) findViewById(R.id.imgBlueQuare);
        imgBlueSquare3 = (ImageView) findViewById(R.id.imgBlueQuare);
        imgBlueSquare4 = (ImageView) findViewById(R.id.imgBlueQuare);
        imgBlueSquare5 = (ImageView) findViewById(R.id.imgBlueQuare);
        imgGetter1 = (ImageView) findViewById(R.id.imgGetter1);
        imgGetter2 = (ImageView) findViewById(R.id.imgGetter2);
        imgGetter3 = (ImageView) findViewById(R.id.imgGetter3);
        imgGetter4 = (ImageView) findViewById(R.id.imgGetter4);
        imgGetter5 = (ImageView) findViewById(R.id.imgGetter5);
        imgClaps = (ImageView) findViewById(R.id.imgClaps);
        imgBackground = (ImageView) findViewById(R.id.imgBackground);
        imgBackgroundMain = (ImageView) findViewById(R.id.imgBackgroundMain);

        roBlueSquare1 = (RelativeLayout) findViewById(R.id.roBlueSquare1);
        roBlueSquare2 = (RelativeLayout) findViewById(R.id.roBlueSquare2);
        roBlueSquare3 = (RelativeLayout) findViewById(R.id.roBlueSquare3);
        roBlueSquare4 = (RelativeLayout) findViewById(R.id.roBlueSquare4);
        roBlueSquare5 = (RelativeLayout) findViewById(R.id.roBlueSquare5);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);

        loTarget1 = (LinearLayout) findViewById(R.id.loTarget1);
        loTarget2 = (LinearLayout) findViewById(R.id.loTarget2);
        loTarget3 = (LinearLayout) findViewById(R.id.loTarget3);
        loTarget4 = (LinearLayout) findViewById(R.id.loTarget4);
        loTarget5 = (LinearLayout) findViewById(R.id.loTarget5);
        loFatherTarget = (LinearLayout) findViewById(R.id.loFatherTarget);
        loFatherDrag = (LinearLayout) findViewById(R.id.loFatherDrag);


        tvBlueQuareText = (TextView) findViewById(R.id.tvBlueQuareText);
        tvBlueQuareText2 = (TextView) findViewById(R.id.tvBlueQuareText2);
        tvBlueQuareText3 = (TextView) findViewById(R.id.tvBlueQuareText3);
        tvBlueQuareText4 = (TextView) findViewById(R.id.tvBlueQuareText4);
        tvBlueQuareText5 = (TextView) findViewById(R.id.tvBlueQuareText5);
        tvAlgorithmName = (TextView) findViewById(R.id.tvAlgorithmName);
        tvIteration = (TextView) findViewById(R.id.tvIteration);
        tvYourResult = (TextView) findViewById(R.id.tvYourResult);
        tvSortResult = (TextView) findViewById(R.id.tvArrayResult);

        roBlueSquare1.setOnTouchListener(this);
        roBlueSquare2.setOnTouchListener(this);
        roBlueSquare3.setOnTouchListener(this);
        roBlueSquare4.setOnTouchListener(this);
        roBlueSquare5.setOnTouchListener(this);

        loTarget1.setOnDragListener(dragListener);
        loTarget2.setOnDragListener(dragListener);
        loTarget3.setOnDragListener(dragListener);
        loTarget4.setOnDragListener(dragListener);
        loTarget5.setOnDragListener(dragListener);

        startButton = (Button) findViewById(R.id.startButton);
        btnSee = (Button) findViewById(R.id.btnSee);
        btnGuide = (Button) findViewById(R.id.btnInstruction);
        btnBack = (Button) findViewById(R.id.btnBack);
    }
    public void start(View view){

        if(!once)myMedia2.start();
        once= true;
        startButton.setVisibility(view.INVISIBLE);
        btnGuide.setVisibility(view.INVISIBLE);
        imgBackgroundMain.setVisibility(view.INVISIBLE);
        gameRelativeLayout.setVisibility(view.VISIBLE);
        imgBackground.setVisibility(view.VISIBLE);
        algorithms();

    }
    public void guide(View view){
        myMedia2.start();
        Intent intent = new Intent(this, GuideMenu.class);
        startActivity(intent);
    }
    public void mostrarResultados(){

        switch(ite1)
        {
            case 0:
                if(algoritmito.getNumIteraciones() - 1 == 0)
                    tvSortResult.setText("The sorted array is: " + algoritmito.getIt1());
                else
                tvSortResult.setText("The sorted array so far is: " + algoritmito.getIt1()); break;
            case 1:
                if(algoritmito.getNumIteraciones() - 1 == 0)
                    tvSortResult.setText("The sorted array is: " + algoritmito.getIt2());
                else
                    tvSortResult.setText("The sorted array so far is: " + algoritmito.getIt2());; break;
            case 2:
                if(algoritmito.getNumIteraciones() - 1 == 0)
                    tvSortResult.setText("The sorted array is: " + algoritmito.getIt3());
                else
                    tvSortResult.setText("The sorted array so far is: " + algoritmito.getIt3());; break;
            case 3:
                if(algoritmito.getNumIteraciones() - 1 == 0)
                    tvSortResult.setText("The sorted array is: " + algoritmito.getIt4());
                else
                    tvSortResult.setText("The sorted array so far is: " + algoritmito.getIt4());break;
        }

        tvYourResult.setText("Your array is: " + String.valueOf(yourArray[0]) + " " +
                String.valueOf(yourArray[1]) + " " +
                String.valueOf(yourArray[2]) + " " + String.valueOf(yourArray[3]) + " " +
                String.valueOf(yourArray[4]));
    }
    public boolean Results(){

        switch(sortAlgorithm)
        {
            case "modifiedBubbleSort":
                result = algoritmito.ordBurbujaModificado(arrInt, yourArray);
                mostrarResultados();
                break;
            case "selectionSort":
                result = algoritmito.selectionSort(arrInt, yourArray);
                mostrarResultados();
                break;
            case "insertionSort":
                result = algoritmito.insertionSort(arrInt, yourArray);
                mostrarResultados();
                break;
            case "cocktailSort":
                result = algoritmito.cocktailSort(arrInt, yourArray);
                mostrarResultados();
                break;
        }
        return result;
    }

    public void algorithms(){
        Random randomGenerator = new Random();
        int arrInt2[] = new int[5];

        if(!Datos.getPasa()) {

            for (int i = 0; i < 5; i++) {

                arrInt[i] = randomGenerator.nextInt(10);
                arrInt2[i] = arrInt[i];
            }

            randomInt = randomGenerator.nextInt(4);

            tvIteration.setText("number of iterations left:");

            switch(randomInt){
                case 0: tvIteration.setText(tvIteration.getText() + " " + String.valueOf(algoritmito.bubbleSortImproved(arrInt2)));
                    break;
                case 1: tvIteration.setText(tvIteration.getText() + " " + String.valueOf(algoritmito.ordSeleccion(arrInt2)));
                    break;
                case 2: tvIteration.setText(tvIteration.getText() + " " + String.valueOf(algoritmito.insercionSort(arrInt2)));
                    break;
                case 3: tvIteration.setText(tvIteration.getText() + " " + String.valueOf(algoritmito.cocktelSort(arrInt2)));
                    break;
            }
        }

        switch (ite1){

            case 0: tvBlueQuareText.setText(String.valueOf(arrInt[0]));
                tvBlueQuareText2.setText(String.valueOf(arrInt[1]));
                tvBlueQuareText3.setText(String.valueOf(arrInt[2]));
                tvBlueQuareText4.setText(String.valueOf(arrInt[3]));
                tvBlueQuareText5.setText(String.valueOf(arrInt[4])); ;break;
            case 1: actualizarArreglo(algoritmito.getArr1()); break;
            case 2: actualizarArreglo(algoritmito.getArr2()); break;
            case 3: actualizarArreglo(algoritmito.getArr3()); break;
            case 4: actualizarArreglo(algoritmito.getArr4()); break;
        }

        switch (randomInt) {

            case 0:
                setLayout("modifiedBubbleSort");
                imgBackground.setImageResource(R.drawable.bubble_background);
                break;
            case 1:
                setLayout("selectionSort");
                imgBackground.setImageResource(R.drawable.selection_background);
                break;
            case 2:
                setLayout("insertionSort");
                imgBackground.setImageResource(R.drawable.insertion_background);
                break;
            case 3:
                setLayout("cocktailSort");
                imgBackground.setImageResource(R.drawable.cocktail_background);
                break;
        }
    }

    public void actualizarArreglo(ArrayList<Integer>arrayList){

        tvBlueQuareText.setText(String.valueOf(arrayList.get(0)));
        tvBlueQuareText2.setText(String.valueOf(arrayList.get(1)));
        tvBlueQuareText3.setText(String.valueOf(arrayList.get(2)));
        tvBlueQuareText4.setText(String.valueOf(arrayList.get(3)));
        tvBlueQuareText5.setText(String.valueOf(arrayList.get(4)));
    }
    public void setLayout(String text){

        tvAlgorithmName.setText(text);
        imgBackground.animate().alpha(1f).setDuration(2000);
        gameRelativeLayout.animate().alpha(1f).setDuration(1000);
        sortAlgorithm = tvAlgorithmName.getText().toString();
        algorithmType = text;
    }
    public void seeAlgorithm(View view){
        myMedia2.start();
        Intent intent = new Intent(this, AlgoritmosDeOrdenamiento.class);
        startActivity(intent);
    }

    boolean moving = false;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                moving = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if(moving){
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(view);
                    view.startDrag(data, myShadowBuilder, view, 0); //0 flag
                }
                break;
            case MotionEvent.ACTION_UP:
                moving = false;
                break;
        }
        return true;
    }

    public void addValue(View myView, View view, ImageView myImage){

        LinearLayout oldParent = (LinearLayout) myView.getParent();
        oldParent.removeView(myView);
        LinearLayout newParent = (LinearLayout) view;
        myImage.setVisibility(View.GONE);
        newParent.addView(myView);

    }
    public void animationResult(){

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int height = size.y;

        loFatherTarget.animate().translationYBy(-height/3f).scaleY(0.7f).scaleX(0.7f).setDuration(2000).
                withEndAction(new Runnable() {
                    @Override
                    public void run() {

                        tvYourResult.setVisibility(View.VISIBLE);
                        tvSortResult.setVisibility(View.VISIBLE);

                        tvYourResult.animate().alpha(1f).setDuration(2000);
                        tvSortResult.animate().alpha(1f).setDuration(2000);

                        imgClaps.animate().alpha(1f).setDuration(4000).
                                withEndAction(new Runnable() {
                                    @Override
                                    public void run() {

                                        setContentView(R.layout.activity_main);
                                        inicializarDatos();

                                        if(algoritmito.getNumIteraciones() - 1 >= 1){

                                            ite1++;
                                            Datos.setPasa(true);
                                            algoritmito.setNumIteraciones(algoritmito.getNumIteraciones() - 1);
                                        } else{

                                            algoritmito.setNumIteraciones(0);
                                            arrInt = null;
                                            yourArray = null;
                                            arrInt = new int[5];
                                            yourArray = new int[5];
                                            result = false;
                                            Datos.setPasa(false);
                                            algoritmito.setEndaux(4);
                                            algoritmito.setStartaux(0);
                                            ite1 = 0;
                                        }

                                        i = 0;
                                        start(startButton);
                                        tvIteration.setText("number of iterations left:");
                                        tvIteration.setText(tvIteration.getText() + " " + String.valueOf(algoritmito.getNumIteraciones()));

                                    }
                                }). start();

                    }
                }). start();
    }
    public void getChild(View myView, View view){

        String text = "";

        for(int i=0; i<((ViewGroup)myView).getChildCount(); ++i) {
            View nextChild = ((ViewGroup)myView).getChildAt(i);
            switch (nextChild.getId())
            {
                case R.id.tvBlueQuareText:
                    text = ((TextView) nextChild).getText().toString(); break;
                case R.id.tvBlueQuareText2:
                    text = ((TextView) nextChild).getText().toString(); break;
                case R.id.tvBlueQuareText3:
                    text = ((TextView) nextChild).getText().toString(); break;
                case R.id.tvBlueQuareText4:
                    text = ((TextView) nextChild).getText().toString(); break;
                case R.id.tvBlueQuareText5:
                    text = ((TextView) nextChild).getText().toString(); break;
            }
        }
        switch (view.getId()){
            case R.id.loTarget1:
                yourArray[0] = Integer.parseInt(text);
                break;
            case R.id.loTarget2:
                yourArray[1] = Integer.parseInt(text);
                break;
            case R.id.loTarget3:
                yourArray[2] = Integer.parseInt(text);
                break;
            case R.id.loTarget4:
                yourArray[3] = Integer.parseInt(text);
                break;
            case R.id.loTarget5:
                yourArray[4] = Integer.parseInt(text);
                break;
        }
    }
    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {

            int event = dragEvent.getAction();
            final View myView = (View) dragEvent.getLocalState();

            switch(event){

                case DragEvent.ACTION_DROP:

                    if(view.getId() == R.id.loTarget1){

                        myMedia3.start();
                        getChild(myView, view);
                        ++i; addValue(myView, view, imgGetter1);
                    } else if(view.getId() == R.id.loTarget2){

                        myMedia3.start();
                        getChild(myView, view);
                        ++i; addValue(myView, view, imgGetter2);
                    } else if(view.getId() == R.id.loTarget3){

                        myMedia3.start();
                        getChild(myView, view);
                        ++i; addValue(myView, view, imgGetter3);
                    } else if(view.getId() == R.id.loTarget4){

                        myMedia3.start();
                        getChild(myView, view);
                        ++i; addValue(myView, view, imgGetter4);
                    } else if(view.getId() == R.id.loTarget5){

                        myMedia3.start();
                        getChild(myView, view);
                        ++i; addValue(myView, view, imgGetter5);
                    }
                    if(i == 5){
                        if(Results())
                        {
                            myMedia = MediaPlayer.create(MainActivity.this, R.raw.applause_2);
                            myMedia.start();
                            imgClaps.setImageResource(R.drawable.claps);
                            animationResult();

                        } else{
                            myMedia = MediaPlayer.create(MainActivity.this, R.raw.boo);
                            myMedia.start();
                            imgClaps.setImageResource(R.drawable.wrong);
                            animationResult();
                        }
                    }
                    break;
            }
            return true;
        }
    };
}
