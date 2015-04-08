package com.aitorzubizarreta.geolocation2;

import android.content.Context;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MapsActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
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
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        createGoogleAPIClient();
        createLocationRequest();
    }

    private void createLocationRequest() {
        myLocationRequest = new LocationRequest();
        myLocationRequest.setInterval(10000);
        myLocationRequest.setFastestInterval(10000);
        myLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void createGoogleAPIClient() {
        // Build a Google Client to interact with Google API
        myGoogleAPIClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        myGoogleAPIClient.connect();
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
        if (myGoogleAPIClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(myGoogleAPIClient, this);
            myGoogleAPIClient.disconnect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this, "Conectado el GPS", Toast.LENGTH_SHORT).show();
        Location myLastLocation = LocationServices.FusedLocationApi.getLastLocation(myGoogleAPIClient);
        if (myLastLocation != null) {
            handleLocation(myLastLocation);
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(myGoogleAPIClient, myLocationRequest, this);
    }

    private void handleLocation(Location myLastLocation) {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(myLastLocation.getLatitude(), myLastLocation.getLongitude()))
                .title("Marker")
                .snippet("Lat:" + myLastLocation.getLatitude() + " Lng:" + myLastLocation.getLongitude() + "Speed:" + myLastLocation.getSpeed() + "" + myLastLocation.getTime()));

        coordenades = "Lat:" + myLastLocation.getLatitude() + " Lng:" + myLastLocation.getLongitude() + "\n";
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
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        handleLocation(location);
        Toast.makeText(this, "New Location", Toast.LENGTH_SHORT);
    }
}
