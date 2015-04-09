package com.aitorzubizarreta.geolocation3;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aitorzubizarreta.geolocation3.Adapter.PlaceAdapter;
import com.aitorzubizarreta.geolocation3.Model.Place;

import java.security.PrivateKey;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    // Elementos de la UI
    private ListView lista;

    // Variables
    private ArrayList<Place> placesList;

    // Creamos el adaptador que comunicará los datos (ArrayList, BD) con la vista.
    private PlaceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializo el ArrayList y el adapter
        placesList = new ArrayList<Place>();
        adapter  = new PlaceAdapter(this, R.layout.place_info, placesList); // Contexto o actividad, layout y datos.

        getUIElements();
        loadArrayList();
    }
    private void getUIElements() {
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adapter);
    }
    private void loadArrayList() {
        // Rellenamos de datos
        Place myPlace = new Place("Kursaal", 43.324568, -1.977581);
        placesList.add(myPlace);
        Place myPlace2 = new Place("La Concha", 43.3180289, -1.9916765);
        placesList.add(myPlace2);
        placesList.add(myPlace2);
        placesList.add(myPlace2);
        placesList.add(myPlace2);
        placesList.add(myPlace2);
        placesList.add(myPlace2);
        placesList.add(myPlace2);
        placesList.add(myPlace2);
        placesList.add(myPlace2);
        placesList.add(myPlace2);
        placesList.add(myPlace2);
        placesList.add(myPlace2);
        placesList.add(myPlace2);
        placesList.add(myPlace2);
        placesList.add(myPlace2);
        placesList.add(myPlace2);
        placesList.add(myPlace2);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
