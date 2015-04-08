package com.aitorzubizarreta.geolocation3.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aitorzubizarreta.geolocation3.Model.Place;
import com.aitorzubizarreta.geolocation3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cursomovil on 8/04/15.
 */
public class PlaceAdapter extends ArrayAdapter<Place> {

    // Constructor
    public PlaceAdapter(Context context, ArrayList<Place> places) {
        super(context, 0, places);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        //Place myPlace = getPlace(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.place_info, parent, false);
        }

        // Lookup view for data population
        TextView placeName = (TextView)convertView.findViewById(R.id.placeNameTextView);
        TextView placeLat = (TextView)convertView.findViewById(R.id.placeLatTextView);
        TextView placeLong = (TextView)convertView.findViewById(R.id.placeLngTextView);

        // Populate the data into the template view using the data object
        placeName.setText("Name");
        placeLat.setText("Lat");
        placeLong.setText("Long");

        return convertView;
    }
}
