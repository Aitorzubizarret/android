package com.aitorzubizarreta.geolocation3.Adapter;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aitorzubizarreta.geolocation3.Model.Place;
import com.aitorzubizarreta.geolocation3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cursomovil on 8/04/15.
 */
public class PlaceAdapter extends ArrayAdapter<Place> {

    private int resource;

    // Constructor
    public PlaceAdapter(Context context, int resource, ArrayList<Place> places) {
        super(context, 0, places);
        this.resource = resource; // layout
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtenemos el objeto lugar
        Place myPlace = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(this.resource, parent, false);
        }

        // Capturamos los elementos UI de la vista
        TextView placeName = (TextView)convertView.findViewById(R.id.placeNameTextView);
        TextView placeLat = (TextView)convertView.findViewById(R.id.placeLatTextView);
        TextView placeLong = (TextView)convertView.findViewById(R.id.placeLngTextView);

        // Rellenamos los datos de la plantilla con los del objeto
        placeName.setText(myPlace.getName());
        placeLat.setText("Lat: " + String.valueOf(myPlace.getLatitude()));
        placeLong.setText("Lng: " + String.valueOf(myPlace.getLongitude()));

        return convertView;
    }
}
