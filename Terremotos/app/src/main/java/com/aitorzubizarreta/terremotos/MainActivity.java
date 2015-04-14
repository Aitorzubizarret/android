package com.aitorzubizarreta.terremotos;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.aitorzubizarreta.terremotos.Fragments.ListaFragment;
import com.aitorzubizarreta.terremotos.Fragments.MapaFragment;


public class MainActivity extends Activity {

    // Constants
    private static final String SELECTED_TAB = "selectedTab"; // Bundle saveInstanteState
    private static final String CONSOLE = "console"; // Log.d

    // Var
    private ActionBar.TabListener tabListener1, tabListener2;
    private ActionBar actionBar;
    private ActionBar.Tab tab1, tab2;
    private int selectedTabID = 0;
    private FragmentTransaction ft;
    private ListaFragment lista;
    private MapaFragment mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments();
        createTabListeners();
        createActionBarWithTabs();

        crearNotificacion();

        /*
        * COMPROBAMOS SI TENEMOS DATOS EN EL BUNDLE "SAVEDINSTANCESTATE"
        * Si hay datos obtendremos el indice del tab que estaba seleccionado previamente.
        * */
        if (savedInstanceState != null) {
            selectedTabID = savedInstanceState.getInt(SELECTED_TAB);
        }
        /*
        * SELECCIONAMOS EL TAB BAR
        * Si el usuario ha girado el dispositivo tendremos el tab bar seleccionado dentro del bundle "savedInstanceState"
        * si no, seleccionamos el predeterminado, que es el 0, y esta definido al inicio, cuando creamos la vcariable.
        * */
        actionBar.setSelectedNavigationItem(selectedTabID);
    }
    private void initFragments() {
        lista = new ListaFragment();
        mapa = new MapaFragment();
    }
    private void createTabListeners() {

        tabListener1 = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                // Obtenemos el gestor de fragmentos
                ft = getFragmentManager().beginTransaction();

                ft.replace(R.id.displayFragments, lista).commit();
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };
        tabListener2 = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                // Obtenemos el gestor de fragmentos
                ft = getFragmentManager().beginTransaction();

                ft.replace(R.id.displayFragments, mapa).commit();
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };
    }
    private void createActionBarWithTabs() {
        /*
        * Obtenemos el ActionBar para poder personalizarlo poniendole:
        * - Titulo
        * - Subtitulo
        * - 2 tabs
        * */
        actionBar = getActionBar();
        actionBar.setTitle("Terremotos");
        actionBar.setSubtitle("x Terremotos");
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Creamos el Tab1 -> Lista de terremotos
        tab1 = actionBar.newTab();
        tab1.setText("Lista")
                .setTabListener(tabListener1);
        actionBar.addTab(tab1);

        // Creamos el Tab2 -> Mapa de terremotos
        tab2 = actionBar.newTab();
        tab2.setText("Mapa")
                .setTabListener(tabListener2);
        actionBar.addTab(tab2);
     }

    private void crearNotificacion() {
        // Info -> http://developer.android.com/guide/topics/ui/notifiers/notifications.html

        // Cogemos un constructor de notificaciones
        Notification.Builder builder = new Notification.Builder(MainActivity.this);

        // Con el builder creamos la notificación
        builder.setTicker("Notificación");

        // Creamos una variable de tipo notificación y le asignamos la notificación que hemos hecho
        Notification notification = builder.build();

        // Cogemos el manager de notificaciones
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        // Disparamos la notificación
        notificationManager.notify(1, notification);

        Log.d("Console", "Notificación");
    }
    // Si giramos el dispositivo guardaremos la pestaña seleccionada
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_TAB, actionBar.getSelectedNavigationIndex());
    }

    /*
        * Las funciones
        * - onCreateOptionsMenu
        * - onOptionsUItemSelected
        * Sirven para mostrar el menú de "settings"
        * */
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
