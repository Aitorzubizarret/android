package com.aitorzubizarreta.ejerciciotab1;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Titulo");
        actionBar.setSubtitle("Subtitulo");
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Tab 1
        ActionBar.Tab tabOne = actionBar.newTab();
        tabOne.setText("Tab 1")
            .setTabListener(new TabListener<MyFragment>());
        actionBar.addTab(tabOne);

        // Tab 2
        ActionBar.Tab tabTwo = actionBar.newTab();
        tabTwo.setText("Tab 2")
                .setTabListener(new ActionBar.TabListener() {
                    @Override
                    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                        Toast toast = Toast.makeText(getApplicationContext(), "onTabSelected", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    @Override
                    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
                        Toast toast = Toast.makeText(getApplicationContext(), "onTabUnselected", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    @Override
                    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
                        Toast toast = Toast.makeText(getApplicationContext(), "onTabReselected", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
        actionBar.addTab(tabTwo);
    }
}
