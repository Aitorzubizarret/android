package com.aitorzubizarreta.ejerciciofragments1;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cursomovil on 13/04/15.
 */
public class Pantalla2 extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d("CONSOLE", "Pantalla 2 - onCreate");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Log.d("CONSOLE", "Pantalla 2 - onActivityCreated");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        } else {
            View view = inflater.inflate(R.layout.fragment2, container, false);
            return view;
        }
    }
}
