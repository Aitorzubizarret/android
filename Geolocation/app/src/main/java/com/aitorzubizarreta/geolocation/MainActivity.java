package com.aitorzubizarreta.geolocation;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements com.aitorzubizarreta.geolocation.listeners.LocationListener.AddLocationInterface {

    // Botones de nuestra interfaz
    private TextView lblLat;
    private TextView lblLng;
    private TextView lblAltitude;
    private TextView lblSpeed;

    private String bestProvider;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Capturamos los elementos
        lblLat = (TextView)findViewById(R.id.lblLat);
        lblLng = (TextView)findViewById(R.id.lblLng);
        lblAltitude = (TextView)findViewById(R.id.lblAltitude);
        lblSpeed = (TextView)findViewById(R.id.lblSpeed);

        // Obtenermos el gestor de localización
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        // Especificamos los requisitos de nuestro aplicación para que android nos de el mejor para pedir la localización
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(criteria.POWER_HIGH);
        criteria.setAltitudeRequired(true);
        criteria.setSpeedRequired(true);

        // El sistema nos dará el mejor proveedor
        bestProvider = locationManager.getBestProvider(criteria, true);

        // Obtenemos el proveedor
        LocationProvider provider = locationManager.getProvider(bestProvider);

        // Obtenemos la última localización del proveedor
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        // Comprobamos que tenemos una última localización
        if (location != null) {
            Double lat = location.getLatitude();
            Double lng = location.getLongitude();
            Double altitude = location.getAltitude();
            Float speed = location.getSpeed();
            lblLat.setText(String.valueOf(lat));
            lblLng.setText(String.valueOf(lng));
            lblAltitude.setText(String.valueOf(altitude));
            lblSpeed.setText(String.valueOf(speed));
        }

        Log.d("GEO", "GPS " + bestProvider + " - " + provider);

        listenLocationChanges();
    }

    private void listenLocationChanges() {
        int t = 5000;
        int distance = 5;

        //LocationListener listener = new LocationListener(this);

        //locationManager.requestLocationUpdates(bestProvider, t, distance, listener);
    }

    @Override
    public void addLocation(Location location) {
        lblLat.setText(String.valueOf(location.getLatitude()));
        lblLng.setText(String.valueOf(location.getLongitude()));
        lblAltitude.setText(String.valueOf(location.getAltitude()));
        lblSpeed.setText(String.valueOf(location.getSpeed()));
    }
}
