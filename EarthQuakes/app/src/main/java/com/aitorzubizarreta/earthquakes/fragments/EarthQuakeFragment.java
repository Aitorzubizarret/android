package com.aitorzubizarreta.earthquakes.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.ListFragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.aitorzubizarreta.earthquakes.R;
import com.aitorzubizarreta.earthquakes.adapters.EarthQuakeAdapter;
import com.aitorzubizarreta.earthquakes.model.EarthQuake;
import com.aitorzubizarreta.earthquakes.task.DownloadEarthquakesTask;

import java.util.ArrayList;

/**
 * A fragment representing a list of EarthQuakes.
 */
public class EarthQuakeFragment extends ListFragment {

    private ArrayList<EarthQuake> earthquakes;
    //private ArrayAdapter<EarthQuake> aa;
    private ArrayAdapter<EarthQuake> aa2;
    private SharedPreferences prefs;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EarthQuakeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        earthquakes = new ArrayList<>();

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        /*
        DownloadEarthquakesTask task = new DownloadEarthquakesTask(getActivity(), this);
        task.execute(getString(R.string.earthquakes_url));
        */

        // Creamos un thread para ejecutar la conexión a internet
        // Es necesario. La conexión a internet no se ejecuta en el thread principal
        /*
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                updateEarthquakes();
            }
        });
        // Ejecutamos el thread
        t.start();
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout =  super.onCreateView(inflater, container, savedInstanceState);

        // Instanciar el adapter
        //aa = new ArrayAdapter<EarthQuake>(getActivity(), android.R.layout.simple_list_item_1, earthquakes);
        //setListAdapter(aa);

        // Nuevo adapter personalizado
        aa2 = new EarthQuakeAdapter(getActivity(), R.layout.eartquake_item, earthquakes);
        //aa2 = new ArrayAdapter<EarthQuake>(getActivity(), R.layout.eartquake_item, earthquakes);
        setListAdapter(aa2);

        return layout;
    }
    /*
    @Override
    public void AddEarthQuake(EarthQuake earthquake) {
        double minMagnitude = Double.parseDouble(prefs.getString(getString(R.string.PREF_MIN_MAGNITUDE), "0"));
        if(earthquake.getMagnitude() >= minMagnitude) {
            earthquakes.add(0, earthquake);
            aa2.notifyDataSetChanged();
        }
    }
    */


}
