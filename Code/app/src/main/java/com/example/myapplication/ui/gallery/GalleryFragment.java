package com.example.myapplication.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;


import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

import org.jsoup.select.Elements;

import java.util.ArrayList;




public class GalleryFragment extends Fragment {


    public Elements content;
    public GalleryFragment(){}
    public SelectedItemActivity obj = new SelectedItemActivity();

    ArrayList<String> menuItems =  new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery,container,false);

        final ListView listView = (ListView) view.findViewById(R.id.mainMenu);


        setCategoryInfo();

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,menuItems
        );

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(getActivity(),
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


        return view;

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

    public void setCategoryInfo(){
        menuItems.add("Антисептики");
        menuItems.add("Краски для наружных работ");
        menuItems.add("Краски для внутренних работ");
        menuItems.add("Лаки и масла для полов,лестниц и мебели");
        menuItems.add("Масла для террас и садовой мебели");
        menuItems.add("Шовные массы, герметики");
        menuItems.add("Покрытия для окон и наружных дверей");
        menuItems.add("Материалы для минеральных поверхностей");
        menuItems.add("Гидроизоляция");
    }

}