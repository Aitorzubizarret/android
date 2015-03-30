package com.aitorzubizarreta.sqlite1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private static final String CONSOLE = "Consola";
    private GestorDB gestorDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(CONSOLE, "onCreate()");

        crearBD();
        /*
        insertarPersona("Jon", "Apellido", 20);
        insertarPersona("Juan", "Apellido", 30);
        insertarPersona("Pablo", "Apellido", 24);
        insertarPersona("Maria", "Apellido", 27);
        insertarPersona("Marta", "Apellido", 45);
        insertarPersona("Monica", "Apellido", 18);
        insertarPersona("Mikel", "Apellido", 31);
        insertarPersona("Jose", "Apellido", 76);
        insertarPersona("Manolo", "Apellido", 64);
        */

        mostrarDatosPersonas();
    }

    private void crearBD() {
        // Creamos un objeto de tipo GestorDB, y esta crea la DB
        gestorDB = new GestorDB(this);
    }
    private void insertarPersona(String nombre, String apellido, int edad) {
        gestorDB.insertPerson(nombre, apellido, edad);
    }
    private void borrarPersona() {

    }
    private void modificarPersona() {

    }
    private void mostrarDatosPersonas() {
        gestorDB.getAllPersonas();
    }
}
