package com.example.myapplication.ui.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ui.slideshow.BasketActivity;
import com.example.myapplication.ui.slideshow.SlideshowFragment;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;



public class SelectedProductActivity extends AppCompatActivity {
    Toolbar mToolbar;
    String subItemURL = null;
    ArrayList<String> currentPrice = new ArrayList<>();
    public ArrayList<String> src = new ArrayList<>();
    public Elements flex;

    final String LOG_TAG = "myLogs";

    final String FILENAME = "file";

    final String DIR_SD = "MyFiles";
    final String FILENAME_SD = "fileSD";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_product);
        mToolbar = (Toolbar) findViewById(R.id.toolbar2);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        final Bundle bundle = getIntent().getExtras();


        subItemURL = bundle.getString("Flex");
        //System.out.println(subItemURL);

        mToolbar.setTitle(bundle.getString("ProductSelect"));
        setSupportActionBar(mToolbar);

        Picasso.get().load("http://shop.remmers.by/upload/iblock/2a3/2a348d491e37258f420ffba864220e62.jpg").into(imageView);
       // System.out.println(getURL());

        new Context().execute();

        final Button addItemToBasket = ( Button ) findViewById(R.id.productAdd);

        try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILENAME, MODE_PRIVATE)));
            // пишем данные


            bw.write(bundle.getString("ProductSelect"));
            // закрываем поток
            bw.close();
            Log.d(LOG_TAG, "Файл записан");
        } catch (IOException e) {
            e.printStackTrace();
        }
        readFile();
        addItemToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v, "Товар успешно добавлен в корзину!", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                String shitflex = bundle.getString("ProductSelect");

                Intent intent = new Intent( SelectedProductActivity.this, BasketActivity.class);
                intent.putExtra("Element",shitflex);

                //intent.putExtra("DockInfo", new DocumentInfo(shitflex));
                startActivity(intent);

            }
        });

    }

    public String getURL(){
        return subItemURL;
    }


    private class Context extends AsyncTask<String,Void,ArrayList<String>>{
        @Override
        protected ArrayList<String> doInBackground(String... strings){
            try {
                System.out.println(subItemURL);

                Document doc = Jsoup.connect(getURL()).get();
                flex = doc.select(".prod__ttl-blue");

                for(Element shit : flex ){

                    src.add(shit.text());
                }

                flex = doc.select(".product-item-detail-price-current");
                for(Element shit : flex ){

                    currentPrice.add(shit.text());
                    Intent intent = new Intent( SelectedProductActivity.this,
                            SlideshowFragment.class);
                    //intent.putExtra("");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings){
            super.onPostExecute(strings);

            TextView textView = (TextView) findViewById(R.id.textView);
            TextView articular = (TextView) findViewById(R.id.articular);

            if(src.isEmpty()) onBackPressed();

            else {

                textView.setText(src.get(1));
                articular.setText(src.get(0));
            }
        }
    }


    void readFile() {
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(FILENAME)));
            String str = "";
            // читаем содержимое
            while ((str = br.readLine()) != null) {
                Log.d(LOG_TAG, str);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeFileSD() {
        // проверяем доступность SD
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            return;
        }
        // получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        // создаем каталог
        sdPath.mkdirs();
        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, FILENAME_SD);
        try {
            // открываем поток для записи
            BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
            // пишем данные
            bw.write("Содержимое файла на SD");
            // закрываем поток
            bw.close();
            Log.d(LOG_TAG, "Файл записан на SD: " + sdFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readFileSD() {
        // проверяем доступность SD
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            return;
        }
        // получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, FILENAME_SD);
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new FileReader(sdFile));
            String str = "";
            // читаем содержимое
            while ((str = br.readLine()) != null) {
                Log.d(LOG_TAG, str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
