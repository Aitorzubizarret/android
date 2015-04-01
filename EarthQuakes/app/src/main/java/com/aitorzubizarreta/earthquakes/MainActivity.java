package com.aitorzubizarreta.earthquakes;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aitorzubizarreta.earthquakes.services.DownloadEarthquakesService;
import com.aitorzubizarreta.earthquakes.task.DownloadEarthquakesTask;

public class MainActivity extends ActionBarActivity implements DownloadEarthquakesTask.AddEarthQuakeInterface {

    private static final int PREFS_ACTIVITY = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        DownloadEarthquakesTask task = new DownloadEarthquakesTask(this, this);
        task.execute(getString(R.string.earthquakes_url));
        */

        // DownloadEarthquakesService.java
        Intent download = new Intent(this, DownloadEarthquakesService.class);
        startService(download);
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
        // Este intent es el que recoge la llamada para abrir las preferencias cuando pulsamos el botón menú
        if (id == R.id.action_settings) {
            Intent prefsIntent = new Intent(this, SettingsActivity.class);
            startActivityForResult(prefsIntent, PREFS_ACTIVITY);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("HOLA", "Hemos cerrado la ventana de las preferencias");
    }

    // Descargar de internet los datos y guardarlos en la DB
    // Sacar los metodos del EarthquakeFragment
    // El main implementara la interfaz DownloadEarthquakesTask

    @Override
    public void notifyTotal(Integer count) {
        String msg = getString(R.string.downloadedEarthquakes, count);
        Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        t.show();
    }
}
