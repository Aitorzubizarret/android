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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    // Botones de nuestra interfaz
    private TextView lblLat;
    private TextView lblLng;
    private TextView lblAltitude;
    private TextView lblSpeed;
    private TextView lblConnectionStatus;
    private Button btnConnection;
    private Button btnListen;

    // Google API Client
    private GoogleApiClient myGoogleAPIClient;

    private LocationRequest myLocationRequest;

    // File
    private String filename = "gps-coordenades";
    private FileOutputStream outputStream;
    private String coordenades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createGoogleAPIClient();
        getUIElements();
        setUIListeners();
        createLocationRequest();
    }
    public void createGoogleAPIClient() {
        // Build a Google Client to interact with Google API
        myGoogleAPIClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }
    public void getUIElements() {
        // Get UI Elements
        lblLat = (TextView)findViewById(R.id.lblLat);
        lblLng = (TextView)findViewById(R.id.lblLng);
        lblAltitude = (TextView)findViewById(R.id.lblAltitude);
        lblSpeed = (TextView)findViewById(R.id.lblSpeed);
        lblConnectionStatus = (TextView)findViewById(R.id.location_status_textView);
        btnConnection = (Button)findViewById(R.id.connectBtn);
        btnListen = (Button)findViewById(R.id.listenBtn);
    }
    private void setUIListeners() {
        btnConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myGoogleAPIClient.isConnected()) {
                    disconnectTheClient();
                } else {
                    connectTheClient();
                }
            }
        });
    }
    private void displayLocationStatus(String status) {
        lblConnectionStatus.setText("Status: " + status);
    }
    private void changeConnectionButtonText(String text) {
        btnConnection.setText(text);
    }
    private void connectTheClient() {
        if (!myGoogleAPIClient.isConnected()) {
            myGoogleAPIClient.connect();
            displayLocationStatus("Connecting ...");
            changeConnectionButtonText("Desconectar");
        }
    }
    private void disconnectTheClient() {
        if (myGoogleAPIClient.isConnected()) {
            myGoogleAPIClient.disconnect();
            displayLocationStatus("Disconnecting ...");
            changeConnectionButtonText("Conectar");
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Connect the client
        connectTheClient();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (myGoogleAPIClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(myGoogleAPIClient, this);
            myGoogleAPIClient.disconnect();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        // Disconnect the client
        disconnectTheClient();
    }
    protected void createLocationRequest() {
        myLocationRequest = new LocationRequest();
        myLocationRequest.setInterval(3000);
        myLocationRequest.setFastestInterval(2000);
        myLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
    private void handleNewLocation(Location location) {
        lblLat.setText("Latitude: " + String.valueOf(location.getLatitude()));
        lblLng.setText("Longitude: " + String.valueOf(location.getLongitude()));
        lblAltitude.setText("Altitude: " + String.valueOf(location.getAltitude()));
        lblSpeed.setText("Speed: " + String.valueOf(location.getSpeed()));
        coordenades = "Latitude:" + String.valueOf(location.getLatitude())
                    + " Longitude:" + String.valueOf(location.getLongitude())
                    + " Altitude:" + String.valueOf(location.getAltitude())
                    + " Speed:" + String.valueOf(location.getSpeed())
                    + "\n";

        Toast.makeText(this, "New Location", Toast.LENGTH_LONG).show();
        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(coordenades.getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onConnected(Bundle bundle) {
        displayLocationStatus("Connected!");
        changeConnectionButtonText("Desconectar");
        // Get the last location
        Location myLastLocation = LocationServices.FusedLocationApi.getLastLocation(myGoogleAPIClient);

        // Check if we have received any location
        if (myLastLocation != null) {
            handleNewLocation(myLastLocation);
            LocationServices.FusedLocationApi.requestLocationUpdates(myGoogleAPIClient, myLocationRequest, this);
        }
        Toast.makeText(this, "onConnected", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onConnectionSuspended(int i) {
        displayLocationStatus("Disconnected!");
        changeConnectionButtonText("Conectar");
        Toast.makeText(this, "onConnectionSuspended", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // Error
        displayLocationStatus("Connection Failed");
        Toast.makeText(this, "onConnectionFailed", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
        Toast.makeText(this, "onLocationChanged", Toast.LENGTH_SHORT).show();
    }

}
