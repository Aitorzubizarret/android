package com.aitorzubizarreta.egiteke;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    // Variables
    public static final String CONSOLE = "CONSOLE"; // To filter the output in the console

    private ArrayList<String> todos; // Where we save the TODOs
    private ArrayAdapter<String> aa; // We'll use it to comunicate the ArrayList with the ListView

    private static final String STATE_INPUT_TEXT = "inputText"; // We'll use it to find the value in the InstanceState
    private static final String STATE_LISTVIEW = "listView"; // We'll use it to find the value in the InstanceSate

    // UI Objects
    private EditText todoEditText;
    private Button addTodoBtn;
    private ListView todoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUIElements();
        init();
        todoListView.setAdapter(aa); // Sets the Adapter to the ListView
        putUIElementsListeners();
    }
    private void init() {
        // Initialize the variables
        todos = new ArrayList<>();
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todos);
    }
    private void getUIElements() {
        // Get the UI Elements
        todoEditText = (EditText)findViewById(R.id.todoEditText);
        addTodoBtn = (Button)findViewById(R.id.addTodoBtn);
        todoListView = (ListView)findViewById(R.id.todosListView);
    }
    private void putUIElementsListeners() {
        // Listener to the addTodoButton
        addTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTodo();
            }
        });
    }
    private void saveTodo() {
        // Check if there is any text before saving it
        if (!todoEditText.getText().toString().equals("")) {
            todos.add(0, todoEditText.getText().toString()); // Save it
            aa.notifyDataSetChanged(); // Notify the adapter to update the List View
            todoEditText.setText(""); // Erase the text of the Edit Text
        }
    }

    // Save the information using InstanceState


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_INPUT_TEXT, todoEditText.getText().toString());
        outState.putStringArrayList(STATE_LISTVIEW, todos);
        // Call the super after saving the data
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // Call the super first to receive the data
        super.onRestoreInstanceState(savedInstanceState);

        // Check if there is any data
        if (savedInstanceState != null) {
            todoEditText.setText(savedInstanceState.getString(STATE_INPUT_TEXT));
            todos.addAll(savedInstanceState.getStringArrayList(STATE_LISTVIEW));
        }
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
