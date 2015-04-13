package com.aitorzubizarreta.ejerciciofragments1;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    // UI
    private Button btnChangeFragment;

    // Var
    private int fragmentNumber;
    private FragmentTransaction ft;
    private Pantalla1 p1;
    private Pantalla2 p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Carga "activity_main.xml"
        // Tiene un FrameLayout en el que cargaremos después uno de nuestros fragmentos
        setContentView(R.layout.activity_main);

        getUIElements();
        setUIListeners();
        loadFirstFragment();
    }
    private void getUIElements() {
        btnChangeFragment = (Button)findViewById(R.id.btnChangeFragment);
    }
    private void setUIListeners() {
        btnChangeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment();
            }
        });
    }
    private void loadFirstFragment() {
        // Obtenemos el gestor de fragmentos
        ft = getFragmentManager().beginTransaction();

        createFirstFragment();
        createSecondFragment();

        // Añadimos el fragmento al FrameLayout que tiene la pantalla (containerViewID, fragment)
        ft.add(R.id.displayFragments, p1);

        // Transiccion¿?

        // Ejecutamos
        ft.commit();

        fragmentNumber = 1;
    }
    /*
    private void loadFragment(Fragment f) {
        // Obtenemos el gestor de fragmentos
        ft = getFragmentManager().beginTransaction();

        // Añadimos el fragmento al FrameLayout que tiene la pantalla (containerViewID, fragment)
        ft.add(R.id.displayFragments, p1);

        // Ejecutamos
        ft.commit();
    }
    */
    private void changeFragment() {
        ft = getFragmentManager().beginTransaction();
        if (fragmentNumber == 1) {
            // Reemplazamos el fragmento que tiene el container (container, fragmet)
            ft.replace(R.id.displayFragments,p2);
            fragmentNumber = 2;
        } else {
            // Reemplazamos el fragmento que tiene el container (container, fragmet)
            ft.replace(R.id.displayFragments,p1);
            fragmentNumber = 1;
        }
        ft.commit();
        Toast toast = Toast.makeText(getApplicationContext(), "Cambiado el fragmento", Toast.LENGTH_SHORT);
        toast.show();
    }
    private void createFirstFragment() {
        // Creamos una instancia de Pantalla1, que es una clase que extiende de fragment
        // por lo tanto es un fragmento
        p1 = new Pantalla1();
    }
    private void createSecondFragment() {
        // Creamos una instancia de Pantalla1, que es una clase que extiende de fragment
        // por lo tanto es un fragmento
        p2 = new Pantalla2();
    }
    /*
    private Fragment createFragment(Fragment f) {
        f = new claseDeMiFragmento (Pantalla1 ó Pantalla2)
    }
    */
}
