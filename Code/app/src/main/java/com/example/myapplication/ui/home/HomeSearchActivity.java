package com.example.myapplication.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.ui.gallery.SelectedItemActivity;

import java.util.ArrayList;

public class HomeSearchActivity extends AppCompatActivity {
    SearchView searchView;
    ListView searchListView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    public SelectedItemActivity obj = new SelectedItemActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);

        Toolbar basketToolbar = (Toolbar) findViewById(R.id.homeSearchToolbar);
        basketToolbar.setTitle("Поиск");

        searchView = ( SearchView ) findViewById(R.id.SearchView);
        searchListView = ( ListView ) findViewById(R.id.flex);
        list = new ArrayList<String>();


        list.add("Антисептики");
        list.add("Краски для наружных работ");
        list.add("Краски для внутренних работ");
        list.add("Лаки и масла для полов,лестниц и мебели");
        list.add("Масла для террас и садовой мебели");
        list.add("Шовные массы, герметики");
        list.add("Покрытия для окон и наружных дверей");
        list.add("Материалы для минеральных поверхностей");
        list.add("Гидроизоляция");


        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        searchListView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(HomeSearchActivity.this,
                        SelectedItemActivity.class);
                obj.setCheckPosition(parent.getItemAtPosition(position).toString());
                intent.putExtra("CountryName", parent.getItemAtPosition(position).toString());
                System.out.println(obj.getCheckPosition());
                checkPosition();

                intent.putExtra("Shit", obj.getURL());
                System.out.println(obj.getURL());

                intent.putExtra("ProductPosition",parent.getItemAtPosition(position).toString());

                startActivity(intent);
            }
        });



    }
    public String checkPosition(){
        String sz= obj.getCheckPosition();
        if(sz == "Антисептики"){
            obj.setURL("http://shop.remmers.by/catalog/antiseptiki/");
        }
        if(sz == "Краски для наружных работ"){
            obj.setURL("http://shop.remmers.by/catalog/lazuri_i_kraski_dlya_naruzhnykh_rabot/");
        }
        if(sz == "Краски для внутренних работ"){
            obj.setURL("http://shop.remmers.by/catalog/lazuri_i_kraski_dlya_vnutrennikh_rabot/");
        }
        if(sz == "Лаки и масла для полов,лестниц и мебели"){
            obj.setURL("http://shop.remmers.by/catalog/laki_i_masla_dlya_polov_lestnits_i_mebeli/");
        }
        if(sz == "Масла для террас и садовой мебели"){
            obj.setURL("http://shop.remmers.by/catalog/masla_dlya_terras_i_sadovoy_mebeli/");
        }
        if(sz == "Шовные массы, герметики"){
            obj.setURL("http://shop.remmers.by/catalog/shovnye_massy_germetiki/");
        }
        if(sz == "Покрытия для окон и наружных дверей"){
            obj.setURL("http://shop.remmers.by/catalog/pokrytiya_dlya_okon_i_naruzhnykh_dverey/");
        }
        if(sz == "Материалы для минеральных поверхностей"){
            obj.setURL("http://shop.remmers.by/catalog/materialy_dlya_mineralnykh_poverkhnosey/");
        }
        if(sz == "Гидроизоляция"){
            obj.setURL("http://shop.remmers.by/catalog/gidroikholyatsiya/");
        }
        return sz;

    }

}
