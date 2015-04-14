package com.aitorzubizarreta.terremotos.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.aitorzubizarreta.terremotos.Model.Terremoto;

import java.util.List;

/**
 * Created by cursomovil on 14/04/15.
 */
public class TerremotoAdapter extends ArrayAdapter<Terremoto> {

    // Var
    private int resource;

    public TerremotoAdapter(Context context, int resource, List<Terremoto> objects) {
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
            li = (LayoutInflater)getContext().getSystemService(inflater);
            li.inflate(resource, layout, true);
        } else {
            layout = (LinearLayout)convertView;
        }

        return layout;
    }
}
