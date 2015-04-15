package com.aitorzubizarreta.terremotos.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aitorzubizarreta.terremotos.Model.Terremoto;
import com.aitorzubizarreta.terremotos.R;

import java.util.List;

/**
 * Created by cursomovil on 14/04/15.
 */
public class TerremotoAdapter extends ArrayAdapter<Terremoto> {

    // Var
    private int resource;
    private Terremoto terremoto;
    private TextView terremotoName;

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

        // Obtenemos el terremoto para después obtener sus datos
        terremoto = getItem(position);

        // Obtenemos los elementos del layout (item_terremoto.xml) para poder imprimir los datos en ellos
        terremotoName = (TextView)layout.findViewById(R.id.textViewTerremotoName);

        // Obtenemos los datos del terremoto y se los asignamos a los campos del layout para mostrarlos en pantalla
        terremotoName.setText(terremoto.getName());

        return layout;
    }
}
