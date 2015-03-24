package com.aitorzubizarreta.todolistfragment.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.aitorzubizarreta.todolistfragment.R;
import com.aitorzubizarreta.todolistfragment.model.ToDo;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {

    public interface TODOItemListener {
        public void addTodo(ToDo todo);
    }

    private Button btnAdd;
    private EditText todotext;

    private TODOItemListener target;

    public InputFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            this.target = (TODOItemListener) activity;
        } catch (ClassCastException ex) {
            throw new ClassCastException((activity.toString() + " must implement TODOItemListener"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_input, container, false);

        btnAdd = (Button) layout.findViewById(R.id.btnAdd);
        todotext = (EditText) layout.findViewById(R.id.inputText);

        addEventListener();

        return layout;
    }

    private void addEventListener() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String todo = todotext.getText().toString(); // Cogemos el texto del input
                ToDo todo = new ToDo(todotext.getText().toString());
                target.addTodo(todo);
                todotext.setText(""); // Vaciamos el input para que pueda seguir escribiendo
            }
        });
    }

}
