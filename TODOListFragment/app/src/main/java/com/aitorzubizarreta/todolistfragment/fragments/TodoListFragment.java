package com.aitorzubizarreta.todolistfragment.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aitorzubizarreta.todolistfragment.DetailActivity;
import com.aitorzubizarreta.todolistfragment.R;
import com.aitorzubizarreta.todolistfragment.adapters.ToDoAdapter;
import com.aitorzubizarreta.todolistfragment.model.ToDo;

import java.util.ArrayList;


public class TodoListFragment extends ListFragment implements InputFragment.TODOItemListener {
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    private final String TODOS_KEY = "todos_key";
    public static final String TODO_ITEM = "todo_item";
    private ArrayList<ToDo> todos;
    private ArrayAdapter<ToDo> aa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = super.onCreateView(inflater, container, savedInstanceState);

        todos = new ArrayList<>();
        aa = new ToDoAdapter(getActivity(), R.layout.todo_list_item, todos);

        // Si tenemos un estado guardado, lo cargamos
        if (savedInstanceState != null) {
            // Recordar .addAll -> Para apuntar al mismo lugar de la memoria
            ArrayList<ToDo> tmp = savedInstanceState.getParcelableArrayList(TODOS_KEY);
            todos.addAll(tmp);
        }

        setListAdapter(aa);

        return layout;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ToDo todo = todos.get(position);

        Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
        detailIntent.putExtra(TODO_ITEM, todo);
        startActivity(detailIntent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Si hay un cambio de estado (giramos el dispositivo) guardaremos los datos para cargarlos después
        outState.putParcelableArrayList(TODOS_KEY, todos);
        //outState.putStringArrayList(TODOS_KEY, todos);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void addTodo(ToDo todo) {
        todos.add(0, todo);
        aa.notifyDataSetChanged();

    }
}


