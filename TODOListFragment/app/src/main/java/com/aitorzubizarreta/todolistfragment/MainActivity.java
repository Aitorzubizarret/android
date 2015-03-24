package com.aitorzubizarreta.todolistfragment;

import android.app.Fragment;
import android.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.aitorzubizarreta.todolistfragment.fragments.InputFragment;
import com.aitorzubizarreta.todolistfragment.model.ToDo;

public class MainActivity extends ActionBarActivity implements InputFragment.TODOItemListener {

    private final String TODO = "todo";
    private InputFragment.TODOItemListener listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            listFragment = (InputFragment.TODOItemListener) getFragmentManager().findFragmentById(R.id.listFragment);
        } catch (ClassCastException ex) {
            throw new ClassCastException(this.toString() + " must implement TODOItemListener");
        }
    }

    @Override
    public void addTodo(ToDo todo) {
        listFragment.addTodo(todo);
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
