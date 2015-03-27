package com.aitorzubizarreta.earthquakes;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.aitorzubizarreta.earthquakes.fragments.SettingsFragment;

/**
 * Created by cursomovil on 26/03/15.
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // El siguiente método esta "deprecated" asi que es mejor no usarlo
        //addPreferencesFromResource(R.xml.userpreferences);

        // Display the fragment as the main fragment
        getFragmentManager()
                .beginTransaction()
                    .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

}
