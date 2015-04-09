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
        insertarPersona("Jon", "Garcia", 20);
        insertarPersona("Juan", "Lopez", 30);
        insertarPersona("Pablo", "Fernandez", 24);
        insertarPersona("Maria", "Rodriguez", 27);
        insertarPersona("Marta", "Herrera", 45);
        insertarPersona("Monica", "Martin", 18);
        insertarPersona("Mikel", "Soler", 31);
        insertarPersona("Jose", "Gonzalez", 76);
        insertarPersona("Manolo", "Villa", 64);
        */
        borrarPersonas("Marta");

        mostrarDatosPersonas();
    }

    private void crearBD() {
        // Creamos un objeto de tipo GestorDB, y esta crea la DB
        gestorDB = new GestorDB(this);
    }
    private void insertarPersona(String nombre, String apellido, int edad) {
        gestorDB.insertPerson(nombre, apellido, edad);
    }
    private void borrarPersonas(String nombre) {
        //int personasEliminadas = gestorDB.deletePersons(nombre);
        //Log.d(CONSOLE, "Se han eliminado " + personasEliminadas + " personas.");
    }
    private void modificarPersona() {

    }
    private void mostrarDatosPersonas() {
        gestorDB.getAllPersonas();
    }
}
