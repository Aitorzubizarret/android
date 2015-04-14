package com.aitorzubizarreta.terremotos.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aitorzubizarreta.terremotos.R;

/**
 * Created by cursomovil on 14/04/15.
 */
public class ListaFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Para añadir un botón en el ActionBar
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Cargamos en el container el layout (lista_terremotos.xml) que le pertenece
        if (container == null) {
            return null;
        } else {
            View view = inflater.inflate(R.layout.lista_terremotos, container, false);
            return view;
        }
    }

    // Permite crear menus en el ActionBar
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_refresh, menu);
    }
}
