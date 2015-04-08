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

    // UI Element
    private ListView lista;
    // Variables
    private ArrayList<Place> placesList;
    // Create the adapter to convert the array to views
    private PlaceAdapter adapter = new PlaceAdapter(this, placesList);
    //private ArrayAdapter<String> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUIElements();
        loadArrayList();
        showArrayList();
    }
    private void loadArrayList() {
        // Inicializo el ArrayList
        placesList = new ArrayList<Place>();

        // Rellenamos de datos
        Place myPlace = new Place("Kursaal", 43.324568, -1.977581);
        placesList.add(myPlace);
        Place myPlace2 = new Place("La Concha", 43.3180289, -1.9916765);
        placesList.add(myPlace2);

    }
    private void showArrayList() {
        // Inicializamos el ArrayAdapter


        for (int i=0; i < placesList.size(); i++)  {
            Log.d("Console", placesList.get(i).getName());
        }
    }
    private void getUIElements() {
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adapter);
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
