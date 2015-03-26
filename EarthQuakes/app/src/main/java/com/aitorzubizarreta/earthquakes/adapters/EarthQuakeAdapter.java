package com.aitorzubizarreta.earthquakes.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aitorzubizarreta.earthquakes.R;
import com.aitorzubizarreta.earthquakes.model.EarthQuake;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by cursomovil on 25/03/15.
 */
public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake> {

    private int resource;

    public EarthQuakeAdapter(Context context, int resource, List<EarthQuake> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layout = (LinearLayout) convertView;

        if (convertView == null) {
            // Si no existe la vista
            layout = new LinearLayout(getContext());

            LayoutInflater li;
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            li = (LayoutInflater) getContext().getSystemService(inflater);
            li.inflate(resource, layout, true);
        } else {
            layout = (LinearLayout) convertView;
        }

        EarthQuake earthQuake = getItem(position);
        TextView textViewMagnitude = (TextView) layout.findViewById(R.id.textViewMagnitude);
        TextView textViewLocation = (TextView) layout.findViewById(R.id.textViewLocation);
        TextView textViewDate = (TextView) layout.findViewById(R.id.textViewDate);

        textViewMagnitude.setText(String.valueOf(earthQuake.getMagnitude()));
        textViewLocation.setText(earthQuake.getPlace());
        textViewDate.setText(earthQuake.getTime().toString());

        return layout;
    }
}
