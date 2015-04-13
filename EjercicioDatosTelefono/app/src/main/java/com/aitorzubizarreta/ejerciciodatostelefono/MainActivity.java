package com.aitorzubizarreta.ejerciciodatostelefono;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos el gestor de telefonia
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);

        // Número de teléfono
        String number = telephonyManager.getLine1Number();
        Log.d("Console", "Núm: " + number);

        Log.d("Console", "Device ID: " + telephonyManager.getDeviceId());

        Log.d("Console", "Device Soft Version: " + telephonyManager.getDeviceSoftwareVersion());

        Log.d("Console", "Line number: " + telephonyManager.getLine1Number());

        Log.d("Console", "SIM state: " + telephonyManager.getSimState());

        Log.d("Console", "Network type: " + telephonyManager.getNetworkType());

        Log.d("Console", "Network type: " + telephonyManager.getNetworkOperatorName());
    }
}
