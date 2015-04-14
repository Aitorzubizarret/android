package com.aitorzubizarreta.ejerciciotab1;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    // Var
    private static final String SELECTED_TAB = "selectedTab";
    private int selectedTabID = 0;

    private ActionBar actionBar;
    private ActionBar.Tab tab1, tab2;
    private ActionBar.TabListener tabListener1, tabListener2;
    private FragmentTransaction ft;
    private Fragment1 f1;
    private Fragment2 f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createListeners();
        addTabsToActionBar();

        /*
        * COMPROVAMOS SI TENEMOS DATOS EN EL BUNDLE "SAVEDINSTANCESTATE"
        * Si hay datos obtendremos el indice del tab que estaba seleccionado previamente.
        * */
        if (savedInstanceState != null) {
            selectedTabID = savedInstanceState.getInt(SELECTED_TAB);
            Log.d("Console", String.valueOf(selectedTabID));
        }

        /*
        * SELECCIONAMOS EL TAB BAR
        * Si el usuario ha girado el dispositivo tendremos el tab bar seleccionado dentro del bundle "savedInstanceState"
        * si no, seleccionamos el predeterminado, que es el 0, y esta definido al inicio, cuando creamos la vcariable.
        * */
        actionBar.setSelectedNavigationItem(selectedTabID);
    }
    private void addTabsToActionBar() {
        // Obtenemos el ActionBar y lo personalizamos con un titulo y un subtitulo
        // También lo cambiamos el modo a navegación con pestañas
        actionBar = getActionBar();
        actionBar.setTitle("Titulo");
        actionBar.setSubtitle("Subtitle");
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Creamos la TAB 1 y la añadimos al ActionBar
        tab1 = actionBar.newTab();
        tab1.setText("Tab 1")
                .setTabListener(tabListener1);
        actionBar.addTab(tab1);

        // Creamos la TAB 2 y la añadimos al ActionBar
        tab2 = actionBar.newTab();
        tab2.setText("Tab 2")
                .setTabListener(tabListener2);
        actionBar.addTab(tab2);
    }
    private void createListeners() {
        tabListener1 = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                Toast toast = Toast.makeText(getApplicationContext(), "Tab1", Toast.LENGTH_SHORT);
                toast.show();

                // Cargamos el fragmento 1
                ft = getFragmentManager().beginTransaction();

                if (f1 == null) {
                    f1 = new Fragment1();
                }
                ft.replace(R.id.displayFragment, f1).commit();
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };

        tabListener2 = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                Toast toast = Toast.makeText(getApplicationContext(), "Tab2", Toast.LENGTH_SHORT);
                toast.show();

                // Cargamos el fragmento 2
                ft = getFragmentManager().beginTransaction();

                if (f2 == null) {
                    f2 = new Fragment2();
                }

                ft.replace(R.id.displayFragment, f2).commit();
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_TAB, actionBar.getSelectedNavigationIndex());
        //Log.d("Console", "onSaveInstanceState " + actionBar.getSelectedNavigationIndex());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //Log.d("Console", "onRestoreInstanceState");
    }
}
