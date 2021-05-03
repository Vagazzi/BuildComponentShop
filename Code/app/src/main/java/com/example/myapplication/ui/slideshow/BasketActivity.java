package com.example.myapplication.ui.slideshow;

import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import android.widget.ImageButton;
import android.widget.ListView;

import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.Product;
import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;


import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity {

    ArrayList<Product> products = new ArrayList<Product>();
    BoxAdapter boxAdapter;
    Bundle bundle = new Bundle();
    String s;
    int size = 1;
    String str = "";
    int countLine = 0;
    final String LOG_TAG = "myLogs";

    final String FILENAME = "file";

    final String DIR_SD = "MyFiles";
    final String FILENAME_SD = "fileSD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);


        Toolbar basketToolbar = (Toolbar) findViewById(R.id.basketToolbar);
        basketToolbar.setTitle("Корзина");
        /*
        if(bundle.getString("Element")== null)
        {
            onBackPressed();
        }

         */
            readFile();

            System.out.println(s);
            fillData();
            boxAdapter = new BoxAdapter(this, products);

            // настраиваем список
            ListView lvMain = (ListView) findViewById(R.id.basketList);
            lvMain.setAdapter(boxAdapter);



        }

    // генерируем данные для адаптера
    void fillData() {
        for (int i = 1; i <= countLine; i++) {
            products.add(new Product(s, i * 1000,
                    R.drawable.ic_menu_camera, false));
        }
    }

    // выводим информацию о корзине
    public void showResult(View v) {
        String result = "Товары в корзине:";
        for (Product p : boxAdapter.getBox()) {
            if (p.box)
                result += "\n" + p.name;
        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
    public String readFile() {
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(FILENAME)));
            LineNumberReader lineNumberReader = new LineNumberReader(br);

            // читаем содержимое
            while ((str = br.readLine()) != null) {
                Log.d(LOG_TAG, str);
                s=str;
                countLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
    public void writeFile(){
        try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILENAME, MODE_PRIVATE)));
            // пишем данные


            bw.write("");
            // закрываем поток
            bw.close();
            Log.d(LOG_TAG, "Файл записан");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

