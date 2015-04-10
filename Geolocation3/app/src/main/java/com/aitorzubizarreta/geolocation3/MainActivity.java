package com.aitorzubizarreta.geolocation3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

    // Parcelable
    public static final String PARCELABLE_NAME = "parcelable_name";
    public static final String PARCELABLE_LAT = "parcelable_lat";
    public static final String PARCELABLE_LONG = "parcelable_long";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializo el ArrayList y el adapter
        placesList = new ArrayList<Place>();
        adapter  = new PlaceAdapter(this, R.layout.place_info, placesList); // Contexto o actividad, layout y datos.

        getUIElements();
        loadArrayList();
        setUIListeners();
    }

    private void setUIListeners() {
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtenemos la posición del objeto(item) seleccionado en la lista y se la asignamos a un objeto para después obtener los datos a través de él.
                Place selectedPlace = (Place) parent.getItemAtPosition(position);
                // Sacamos un toast por pantalla
                Toast toast = Toast.makeText(getApplicationContext(), selectedPlace.getName(), Toast.LENGTH_SHORT);
                toast.show();
                // Mostramos la pantalla con más info sobre el lugar seleccionado
                //Intent intent = new Intent(MainActivity.this, DetailMapsActivity.class); <-- Google Maps
                Intent intent = new Intent(MainActivity.this, PlaceDetail.class);
                intent.putExtra(PARCELABLE_NAME, selectedPlace.getName());
                intent.putExtra(PARCELABLE_LAT, selectedPlace.getLatitude());
                intent.putExtra(PARCELABLE_LONG, selectedPlace.getLongitude());
                startActivity(intent);
            }
        });
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
        Place myPlace3 = new Place("Buen Pastor", 43.316722, -1.981593);
        placesList.add(myPlace3);
        Place myPlace4 = new Place("Parque Aiete", 43.3051574, -1.9899685);
        placesList.add(myPlace4);
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
