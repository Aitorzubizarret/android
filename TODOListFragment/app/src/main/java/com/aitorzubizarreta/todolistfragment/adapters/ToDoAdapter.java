package com.aitorzubizarreta.todolistfragment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aitorzubizarreta.todolistfragment.R;
import com.aitorzubizarreta.todolistfragment.model.ToDo;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by cursomovil on 23/03/15.
 */
public class ToDoAdapter extends ArrayAdapter<ToDo> {

    private int resource;

    public ToDoAdapter(Context context, int resource, List<ToDo> objects) {
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

        ToDo item = getItem(position);
        TextView lblTask = (TextView) layout.findViewById(R.id.lblTask);
        TextView lblDate = (TextView) layout.findViewById(R.id.lblDate);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        lblTask.setText(item.getTask());
        lblDate.setText(sdf.format(item.getCreated()));

        //return super.getView(position, convertView, parent);
        return layout;
    }
}
