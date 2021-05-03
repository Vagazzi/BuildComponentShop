package com.example.myapplication.ui.slideshow;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.ui.gallery.SelectedProductActivity;
import com.example.myapplication.ui.slideshow.SlideshowViewModel;

import org.greenrobot.eventbus.Subscribe;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private BroadcastReceiver broadcastReceiver;
    public SlideshowFragment(){}

    @Subscribe
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_slideshow,container,false);

        Intent in = new Intent(getActivity(),BasketActivity.class);
        BasketActivity x = new BasketActivity();

        startActivity(in);


        return view;
    }

}