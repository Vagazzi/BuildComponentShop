package com.example.myapplication.ui.gallery;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class SelectedItemActivity extends AppCompatActivity {
    public static final String EXTRA_ITEMID = "itemId";
    Toolbar mToolbar;
    ArrayList<String> menuCurItems =  new ArrayList<>();
    public String URL ;
    String checkPosition = null;
    public Elements content;
    public Elements prices;
    String articule;
    public SelectedItemActivity(){}
    String subItemURL = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_item);
        mToolbar = (Toolbar) findViewById(R.id.toolbar1);

        final Bundle bundle = getIntent().getExtras();

        mToolbar.setTitle(bundle.getString("CountryName"));
        setSupportActionBar(mToolbar);


        final ListView listView = (ListView) findViewById(R.id.newList);

        final ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,menuCurItems
        );

        URL = bundle.getString("Shit");

        new NewThread().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                Intent intent = new Intent( SelectedItemActivity.this,
                        SelectedProductActivity.class);

                String ProductPosition = parent.getItemAtPosition(position).toString();
                intent.putExtra("ProductSelect", ProductPosition);
                System.out.println(ProductPosition);
                String replace = ProductPosition.replace("-", "_");
                setReplace(replace);
                setReplace(getReplace().replace(" ","_"));
                String s1 = getReplace().toLowerCase();
                setReplace(s1);
                String s2 = getReplace().replace("ä","_");
                setReplace(s2);
                String s3 = getReplace().replace("ö","_");
                setReplace(s3);
                String s4 = getReplace().replace("ü","_");
                setReplace(s4);
                String s5 = getReplace().replace("/","_");
                setReplace(s5);
                URL=bundle.getString("Shit");
                String flex = URL + s5 + "/";
                setURL(URL);
                intent.putExtra("Flex",flex);
                System.out.println(URL);
                startActivity(intent);

            }
        });

        listView.setAdapter(listViewAdapter);


    }

    public class NewThread extends AsyncTask<String, Void , ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            Document doc;
            try {
                doc = Jsoup.connect(getURL()).get();
                content = doc.select(".catalog-group__name");

                menuCurItems.clear();

                for (Element contents : content) {
                    menuCurItems.add(contents.text());


                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return menuCurItems;
        }
        protected void onPostExecute(ArrayList<String> output) {

            //Находим ListView
            ListView listview = (ListView) findViewById(R.id.newList);
            //Загружаем в него результат работы doInBackground
            listview.setAdapter(new ArrayAdapter<String>(SelectedItemActivity.this,
                    android.R.layout.simple_list_item_1 , output));

        }

    }


    // Getter
    public String getURL() {
        return URL;
    }
    public String getCheckPosition() {
        return checkPosition;
    }
    public String getReplace(){return subItemURL; }

    // Setter
    public void setURL(String URL) {
        this.URL = URL;
    }
    public void setCheckPosition(String newName) {
        this.checkPosition = newName;
    }
    public void setReplace(String URL) {
        this.subItemURL = URL;
    }

}
