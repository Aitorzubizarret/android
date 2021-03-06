package com.aitorzubizarreta.sqlite1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.StreamHandler;

/**
 * Created by cursomovil on 30/03/15.
 */
public class GestorDB{

    private DBOpenHelper helper;
    private SQLiteDatabase db;
    private static final String CONSOLE = "Consola";

    // Definir las tablas de las columnas de la BD
    public static final String KEY_ID = "_id";
    public static final String KEY_NOMBRE = "name";
    public static final String KEY_APELLIDO = "surname";
    public static final String KEY_EDAD = "age";

    public static final String[] allColumns = {
            KEY_ID,
            KEY_NOMBRE,
            KEY_APELLIDO,
            KEY_EDAD
    };

    // Constructor
    public GestorDB(Context context) {
        this.helper = new DBOpenHelper(context, DBOpenHelper.NombreDB, null, DBOpenHelper.VersionDB); // Creamos la DB
        this.db = helper.getWritableDatabase(); // Obtenemos la BD que acabamos de crear para poder leer y/o escribir en ella
    }
    public void insertPerson(String nombre, String apellido, int edad) {
        // Sin Transaction
        /*
        ContentValues nuevaPersona = new ContentValues();
        nuevaPersona.put("nombre", nombre);
        nuevaPersona.put("apellido", apellido);
        nuevaPersona.put("edad", edad);
        int fila = (int) db.insert(DBOpenHelper.NombreTablaDB, null, nuevaPersona);
        Log.d(CONSOLE, String.valueOf(fila));
        */
        // Utilizando Transaction
        db.beginTransaction();
        try {
            ContentValues nuevaPersona = new ContentValues();
            nuevaPersona.put(KEY_NOMBRE, nombre);
            nuevaPersona.put(KEY_APELLIDO, apellido);
            nuevaPersona.put(KEY_EDAD, edad);
            int fila = (int) db.insert(DBOpenHelper.NombreTablaDB, null, nuevaPersona);
            Log.d(CONSOLE, String.valueOf(fila));
            db.setTransactionSuccessful();
        } catch (Exception e) {
            db.endTransaction();
        } finally {
            db.endTransaction();
        }
    }
    public int deletePersons(String whereClause, ArrayList whereArgs) {
        int contador = 0;

        //db.delete(DBOpenHelper.NombreTablaDB, whereClause, whereArgs);
        /*
        db.beginTransaction();
        try {
            String query = "DELETE FROM " + DBOpenHelper.NombreTablaDB + " WHERE " + KEY_NOMBRE + " LIKE " + nombre;
            Cursor cursor = db.rawQuery(query, null);

        } catch (Exception e) {

        }
        */

        return contador;
    }
    /*
    public int deletePersonsWhereIdIs(ArrayList<Integer> IDs) {
        String whereClause = KEY_ID + "=?";
        int[] whereArgs = IDs;
        deletePersons(whereClause, whereArgs);
        return 1;
    }
    */
    public int deletePersonsWhereNameIs(ArrayList<String> NAMEs) {
        String whereClause = KEY_NOMBRE + "=?";
        return 1;
    }
    public int deletePersonsWhereSurnameIs(ArrayList<String> SURNAMEs) {
        String whereClause = KEY_APELLIDO + "=?";
        return 1;
    }
    public int deletePersonsWhereAgeIs(ArrayList<Integer> AGEs) {
        String whereClause = KEY_EDAD + "=?";
        return 1;
    }

    public void modifyPerson() {

    }
    /*
    public void query() {
        String tableName = DBOpenHelper.NombreTablaDB;
        String[] tableColumns;
        String[] whe;
        Cursor cursor = db.query(tableName, tableColumns, whereClause, whereArgs, groupBy, having, orderBy);
    }
    */
    public void getAllPersonas() {
        Cursor cursor2 = db.rawQuery("Select * from personas", null);
        try {
            //cursor2.moveToFirst();
            while(cursor2.moveToNext()) {
                Log.d(CONSOLE, "ID=" + cursor2.getInt(cursor2.getColumnIndex("_id")) + " | Nombre=" + cursor2.getString(cursor2.getColumnIndex("nombre")) + " | Apellido=" + cursor2.getString(cursor2.getColumnIndex("apellido")) + " | Edad=" + cursor2.getInt(cursor2.getColumnIndex("edad")));
                //cursor2.moveToNext();
            }
        } catch (Exception e) {
            Log.d(CONSOLE, "Error al acceder a los datos de la consulta SELECT");
            Log.e("Base de datos", "Error al acceder a los datos de la consulta SELECT");
        }
    }

    // Creamos una clase dentro nuestra clase para que podamos gestionar la DB sólo desde aqui.
    private static class DBOpenHelper extends SQLiteOpenHelper {

        private static final String NombreDB = "personas.db";
        private static final String NombreTablaDB = "PERSONAS";
        private static final int VersionDB = 1;
        private static final String SentenciaSQLCrearTabla = "CREATE Table " + NombreTablaDB + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NOMBRE + " TEXT, " + KEY_APELLIDO + " TEXT, " + KEY_EDAD + " INTEGER)";

        // Constructor
        public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        /*
        * MÉTODOS
        *
        * onCreate -> Se ejecutará la primera vez que se cree el objeto y crea la DB
        * onUpgrade -> Se ejecturá cuando la DB necesite actualizarse
        * */
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SentenciaSQLCrearTabla);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
