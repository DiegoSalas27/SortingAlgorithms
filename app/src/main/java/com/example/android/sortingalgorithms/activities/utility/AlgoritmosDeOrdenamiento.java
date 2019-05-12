package com.example.android.sortingalgorithms.activities.utility;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.sortingalgorithms.R;
import com.example.android.sortingalgorithms.activities.MainActivity;
import com.example.android.sortingalgorithms.middleware.Datos;

import java.util.ArrayList;

public class AlgoritmosDeOrdenamiento extends AppCompatActivity {

    TextView tvAlgorithmName;
    private String it1, it2, it3, it4 = "";
    private ArrayList<Integer> arr1 = new ArrayList<>();
    private ArrayList<Integer> arr2 = new ArrayList<>();
    private ArrayList<Integer> arr3 = new ArrayList<>();
    private ArrayList<Integer> arr4 = new ArrayList<>();
    int numIteraciones2 = 0;
    boolean correcto = false;
    int startaux = 0;
    int endaux = 4;
    MediaPlayer myMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algoritmos_de_ordenamiento);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/SourceCodeProExtraLight.otf");
        tvAlgorithmName = (TextView) findViewById(R.id.tvAlgorithmName);
        tvAlgorithmName.setTypeface(myCustomFont);

        switch(MainActivity.algorithmType){
            case "modifiedBubbleSort":
                tvAlgorithmName.setText("void bubbleSortModified (int [ ] list, int n)\n" +
                        "{\n" +
                        "\tbool ordered;\n" +
                        "\tfor (int i = 0; i < n - 1; i ++)\n" +
                        "\t{\n" +
                        "\t\tordered = true;\n" +
                        "\t\tfor (int j = 0; j < n - ( i + 1 ); j ++)\n" +
                        "\t\t{\n" +
                        "\t\t\tif (list[ j ] > list[ j + 1 ])\n" +
                        "\t\t\t{\n" +
                        "\t\t\t\tint aux = list[ j ];\n" +
                        "\t\t\t\tlist[ j ] = list[ j + 1 ];\n" +
                        "\t\t\t\tlist[ j + 1 ] = aux;\n" +
                        "\n" +
                        "\n" +
                        "\t\t\t\tordered = false;\n" +
                        "\t\t\t}\n" +
                        "\t\t}\n" +
                        "\t\tif (ordered) break;\n" +
                        "\t}\n" +
                        "}"); break;
            case "selectionSort":
                tvAlgorithmName.setText("void selectionSort(int a[ ], int n)\n" +
                        "{\n" +
                        "\tint k, small;\n" +
                        "\tfor(int i = 0; i < n - 1; i++)\n" +
                        "\t{\n" +
                        "\t\tk = i;\n" +
                        "\t\tsmall = a[i];\n" +
                        "\t\tfor(int j = i + 1; j < n; j++)\n" +
                        "\t\t{\n" +
                        "\t\t\tif(a[j] < small)\n" +
                        "\t\t\t{\n" +
                        "\t\t\t\tsmall = a[j];\n" +
                        "\t\t\t\tk = j;\n" +
                        "\t\t\t}\n" +
                        "\t\t}\n" +
                        "\t\ta[k] = a[i];\n" +
                        "\t\ta[i] = small;\n" +
                        "\t}\n" +
                        "}"); break;
            case "insertionSort":
                tvAlgorithmName.setText("void insertionSort (int a[ ], int n)\n" +
                     "{\n" +
                     "\tint aux, k;\n" +
                     "\tfor (int i=1; i<n; i++)\n" +
                     "\t{\n" +
                     "\t\taux = a[i];\n" +
                     "\t\tk = i-1;\n" +
                     "\t\twhile (k >= 0 && aux < a[k])\n" +
                     "\t\t{\n" +
                     "\t\t\ta[k+1] = a[k];\n" +
                     "\t\t\tk--;\n" +
                     "\t\t}\n" +
                     "\t\ta[k+1] = aux;\n" +
                     "\t}\n" +
                     "}");
                break;
            case "cocktailSort":
                tvAlgorithmName.setText("void CocktailSort(int a[ ], int n)\n" +
                        "{\n" +
                        "    bool swapped = true;\n" +
                        "    int start = 0;\n" +
                        "    int end = n-1;\n" +
                        " \n" +
                        "    while (swapped)\n" +
                        "    {\n" +
                        "        swapped = false;\n" +
                        " \n" +
                        "        for (int i = start; i < end; ++i)\n" +
                        "        {\n" +
                        "            if (a[i] > a[i + 1])\n" +
                        "            {\n" +
                        "                swap(a[i], a[i+1]);\n" +
                        "                swapped = true;\n" +
                        "            }\n" +
                        "        }\n" +
                        " \n" +
                        "        if (!swapped)\n" +
                        "            break;\n" +
                        " \n" +
                        "        swapped = false;\n" +
                        " \n" +
                        "        --end;\n" +
                        " \n" +
                        "        for (int i = end - 1; i >= start; --i)\n" +
                        "        {\n" +
                        "            if (a[i] > a[i + 1])\n" +
                        "            {\n" +
                        "                swap(a[i], a[i+1]);\n" +
                        "                swapped = true;\n" +
                        "            }\n" +
                        "        }\n" +
                        "\n" +
                        "        ++start;\n" +
                        "    }\n" +
                        "}");
                break;
        }
    }

    public void back(View view){
        myMedia = MediaPlayer.create(AlgoritmosDeOrdenamiento.this, R.raw.button3);
        myMedia.start();
        AlgoritmosDeOrdenamiento.this.onBackPressed();
    }

    public void limpiarArreglos(){

        if(!Datos.getPasa()){
            it1 = "";
            it2 = "";
            it3 = "";
            it4 = "";
            arr1 = null;
            arr2 = null;
            arr3 = null;
            arr4 = null;
            arr1 = new ArrayList<>();
            arr2 = new ArrayList<>();
            arr3 = new ArrayList<>();
            arr4 = new ArrayList<>();
        }
    }
    public void compararArreglos(int i, int [] choice, int [] list){

        switch (i){
            case 0:
                int num = 0;
                for(int q = 0; q < 5; q++){ //comprobar

                    if(choice[q] == list[q])
                    {
                        num++;
                        if(num == 5){

                            correcto = true;
                        }
                    }
                } break;
            case 1:
                if(MainActivity.ite1 == 1) {
                    num = 0;
                    for (int q = 0; q < 5; q++) { //comprobar

                        if (choice[q] == arr2.get(q)) {
                            num++;
                            if (num == 5) {

                                correcto = true;
                            }
                        }
                    }
                    break;
                }
            case 2:
                if(MainActivity.ite1 == 2) {
                    num = 0;
                    for (int q = 0; q < 5; q++) { //comprobar

                        if (choice[q] == arr3.get(q)) {
                            num++;
                            if (num == 5) {

                                correcto = true;
                            }
                        }
                    }
                    break;
                }
            case 3:
                if(MainActivity.ite1 == 3) {
                    num = 0;
                    for (int q = 0; q < 5; q++) { //comprobar

                        if (choice[q] == arr4.get(q)) {
                            num++;
                            if (num == 5) {

                                correcto = true;
                            }
                        }
                    }
                    break;
                }
        }

    }
    public void guardarArreglos(int i, int [] list){

        if(!Datos.getPasa()){

            switch (i){ //debe quedar intacto
                case 0:
                    for(int a = 0; a < 5; a++)
                    {
                        it1 +=  String.valueOf(list[a]) + " ";
                        arr1.add(list[a]);
                    } break;
                case 1:
                    for(int a = 0; a < 5; a++)
                    {
                        it2 +=  String.valueOf(list[a]) + " ";
                        arr2.add(list[a]);
                    } break;
                case 2:
                    for(int a = 0; a < 5; a++)
                    {
                        it3 +=  String.valueOf(list[a]) + " ";
                        arr3.add(list[a]);
                    } break;
                case 3:
                    for(int a = 0; a < 5; a++)
                    {
                        it4 +=  String.valueOf(list[a]) + " ";
                        arr4.add(list[a]);
                    } break;
            }
        }

    }

    public boolean ordBurbujaModificado (int [] list, int [] choice) //sirve para hacer las comparaciones entre el usuario y el sistema
    {
        int r = MainActivity.ite1;
        correcto = false;
        boolean ordenado;

        limpiarArreglos();

        for (int i = r; i < list.length-1; i++)
        {
            ordenado = true;
            for (int j = 0; j < list.length - (i + 1); j++)
            {
                if (list[j] > list[j+1])
                {
                    int aux = list[j];
                    list[j] = list[j+1];
                    list[j+1] = aux;

                    ordenado = false;
                }
            }

            guardarArreglos(i, list);

            compararArreglos(i, choice, list);

            if (ordenado) break;
        }

        return correcto;
    }
    public int bubbleSortImproved(int [] list){ //devuelve el numero de iteraciones para mostrar en main activity

        boolean ordenado;
        for (int i=0; i < 5-1; i++)
        {
            ordenado = true;
            for (int j=0; j < 5 - (i + 1); j++)
            {
                if (list[j] > list[j+1])
                {
                    int aux = list[j];
                    list[j] = list[j+1];
                    list[j+1] = aux;
                    ordenado = false;
                }
            }
            numIteraciones2++;
            if (ordenado) break;
        }
        return numIteraciones2;
    }

    public boolean selectionSort(int [] list, int [] choice)
    {
        int r = MainActivity.ite1;
        correcto = false;
        int k, small;

        limpiarArreglos();

        for(int i = r; i < 5 - 1; i++)
        {
            k = i;
            small = list[i];
            for(int j = i + 1; j < 5; j++)
            {
                if(list[j] < small)
                {
                    small = list[j];
                    k = j;
                }
            }
            list[k] = list[i];
            list[i] = small;

            guardarArreglos(i, list);

            compararArreglos(i, choice, list);

        }
        return correcto;
    }
    public int ordSeleccion(int [] list)
    {
        int k, menor;
        for(int i = 0; i < 5 - 1; i++)
        {
            k = i;
            menor = list[i];
            for(int j = i + 1; j < 5; j++)
            {
                if(list[j] < menor)
                {
                    menor = list[j];
                    k = j;
                }
            }
            list[k] = list[i];
            list[i] = menor;
            numIteraciones2++;
        }
        return numIteraciones2;
    }
    public boolean insertionSort (int list[], int [] choice)
    {
        int r = MainActivity.ite1;
        correcto = false;
        int aux, k;

        limpiarArreglos();

        for (int i= r + 1; i<5; i++)
        {
            aux = list[i];
            k = i-1;
            while (k >= 0 && aux < list[k])
            {
                list[k+1] = list[k];
                k--;
            }
            list[k+1] = aux;

            guardarArreglos(i - 1, list);

            compararArreglos(i - 1, choice, list);
        }
        return correcto;
    }
    public int insercionSort (int list[])
    {
        int aux, k;
        for (int i= 1; i<5; i++)
        {
            aux = list[i];
            k = i-1;
            while (k >= 0 && aux < list[k])
            {
                list[k+1] = list[k];
                k--;
            }
            list[k+1] = aux;

            numIteraciones2++;
        }
        return numIteraciones2;
    }
    public boolean  cocktailSort(int [] list, int [] choice, String copyB)
    {
        int r = MainActivity.ite1;
        correcto = false;
        int start = 0;
        int end = 5-1;
        boolean swapped = true;
        int b = 0;
        if (copyB != "") {
            b = r;
        }

        limpiarArreglos();

            while (swapped)
            {
                swapped = false;

                for (int i = start; i < end; ++i) {
                    if (list[i] > list[i + 1]) {
                        int aux = list[i];
                        list[i] = list[i + 1];
                        list[i + 1] = aux;
                        swapped = true;
                    }
                }

                guardarArreglos(b, list);
                switch(r)
                {
                    case 0: compararArreglos(0, choice, list); break;
                    case 1: compararArreglos(1, choice, list); break;
                    case 2: compararArreglos(2, choice, list); break;
                    case 3: compararArreglos(3, choice, list); break;
                }


                b++;

                if (!swapped)
                    break;

                swapped = false;

                --end;

                for (int i = end - 1; i >= start; --i)
                {
                    if (list[i] > list[i + 1])
                    {
                        int aux = list[i];
                        list[i] = list[i+1];
                        list[i+1] = aux;
                        swapped = true;
                    }
                }

                guardarArreglos(b, list);
                switch(r)
                {
                    case 0: compararArreglos(0, choice, list); break;
                    case 1: compararArreglos(1, choice, list); break;
                    case 2: compararArreglos(2, choice, list); break;
                    case 3: compararArreglos(3, choice, list); break;
                }

                b++;

                ++start;

            }
        return correcto;
    }
    public int  cocktelSort(int [] list)
    {

        boolean swapped = true;
        int start = 0;
        int end = 5-1;

        while (swapped)
        {
            swapped = false;

            for (int i = start; i < end; ++i)
            {
                if (list[i] > list[i + 1])
                {
                    int aux = list[i];
                    list[i] = list[i+1];
                    list[i+1] = aux;
                    swapped = true;
                }
            }

            if (!swapped)
                break;

            swapped = false;

            --end;

            numIteraciones2++;

            for (int i = end - 1; i >= start; --i)
            {
                if (list[i] > list[i + 1])
                {
                    int aux = list[i];
                    list[i] = list[i+1];
                    list[i+1] = aux;
                    swapped = true;
                }
            }

            ++start;

            numIteraciones2++;
        }

        return numIteraciones2;
    }

    public void setStartaux(int startaux) {
        this.startaux = startaux;
    }

    public void setEndaux(int endaux) {
        this.endaux = endaux;
    }

    public int getNumIteraciones() {
        return numIteraciones2;
    }

    public void setNumIteraciones(int numIteraciones) {
        this.numIteraciones2 = numIteraciones;
    }

    public String getIt1() {
        return it1;
    }

    public String getIt2() {
        return it2;
    }

    public String getIt3() {
        return it3;
    }

    public String getIt4() {
        return it4;
    }

    public ArrayList<Integer> getArr1() {
        return arr1;
    }

    public ArrayList<Integer> getArr2() {
        return arr2;
    }

    public ArrayList<Integer> getArr3() {
        return arr3;
    }

    public ArrayList<Integer> getArr4() {
        return arr4;
    }

    public void setArr1(ArrayList<Integer> arr1) {
        this.arr1 = arr1;
    }

    public void setArr2(ArrayList<Integer> arr2) {
        this.arr2 = arr2;
    }

    public void setArr3(ArrayList<Integer> arr3) {
        this.arr3 = arr3;
    }

    public void setArr4(ArrayList<Integer> arr4) {
        this.arr4 = arr4;
    }

    public AlgoritmosDeOrdenamiento() {
    }
}
