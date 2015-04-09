package com.aitorzubizarreta.geolocation3;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class PlaceDetail extends ActionBarActivity {

    // UI Elements
    private TextView placeName;
    private TextView placeLat;
    private TextView placeLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        getUIElements();

        // Obtenemos los datos del intent
        Bundle placeData = this.getIntent().getExtras();

        placeName.setText(placeData.getString(MainActivity.PARCELABLE_NAME));
        //placeLat.setText(placeData.getDouble(MainActivity.PARCELABRE_LAT));

    }

    private void getUIElements() {
        placeName = (TextView)findViewById(R.id.lblPlaceName);
        placeLat = (TextView)findViewById(R.id.lblPlaceLat);
        placeLong = (TextView)findViewById(R.id.lblPlaceLong);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_place_detail, menu);
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
