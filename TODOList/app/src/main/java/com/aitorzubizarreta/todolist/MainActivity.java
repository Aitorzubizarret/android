package com.aitorzubizarreta.todolist;

import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    // Variables
    private ArrayList<String> todos;
    /*
    * En savedInstanceState podemos guardar muchas cosas, y para recuperarlas necesitamos un key
    * Y para ello creamos el siguiente, todoDATA
    * */
    private final String todosKey = "todosArray";
    private Button btnAdd;
    private TextView todoText;
    private ListView todoListView;
    private ArrayAdapter<String> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializamos nuestras variable
        todos = new ArrayList<String>();
        /*
        * CUIDADO!
        * El adapter se queda con la dirección a la que apunta todos.
        * Tendremos que utilizar la función .addAll para no perder esa dirección.
        * */
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todos);

        // Obtenemos los objetos que necesitamos
        btnAdd = (Button) findViewById(R.id.btnAdd);
        todoText = (TextView) findViewById(R.id.todoText);
        todoListView = (ListView) findViewById(R.id.todoListView);

        todoListView.setAdapter(aa);

        // Para no llenar el onCreate de código, crearemos un método que será el encargado
        // de crear los listeners
        this.addEventListeners();

    }

    // Método encargado de crear los listeners
    private void addEventListeners() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtendremos el texto que ha escrito el usuario en el input
                String todo = todoText.getText().toString();

                // Guardamos los datos
                todos.add(0, todo);

                // Avisar al adapter
                aa.notifyDataSetChanged();

                // Borrar el input
                todoText.setText("");

            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList(todosKey, todos);
        Log.d("CHANGE", "Aqui deberiamos guardar los datos");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        /*
        * Si solo ponemos todos cambiamos el puntero de lugar y tendriamos problemas
        * Tenemos que utilizar el método .addAll para añadir
        * */
        todos.addAll(savedInstanceState.getStringArrayList(todosKey));
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
