package com.aitorzubizarreta.earthquakes.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;


import com.aitorzubizarreta.earthquakes.R;


public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(this);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.userpreferences);
    }

    // Mover
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d("Hola", "Hemos cambiado las preferencias");

        String PREF_AUTO_UPDATE = getString(R.string.PREF_AUTO_UPDATE);
        String PREF_UPDATE_INTERVAL = getString(R.string.PREF_UPDATE_INTERVAL);
        String PREF_MIN_MAGNITUDE = getString(R.string.PREF_MIN_MAGNITUDE);

        if (key.equals(PREF_AUTO_UPDATE)) {
            // Switch on/off
        } else if (key.equals(PREF_UPDATE_INTERVAL)) {
            // Change autorefresh interval
        } else if (key.equals(PREF_MIN_MAGNITUDE)) {
            // Update earthquake listview
            double minMag = Double.parseDouble(sharedPreferences.getString(key, "0"));

            /*
            * Todos pueden ver las sharedPreferences, por lo tanto no hace falta que las guardemos aqui
            * */
        }
    }
}