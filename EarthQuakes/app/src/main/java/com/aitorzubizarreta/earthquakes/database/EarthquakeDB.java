package com.aitorzubizarreta.earthquakes.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cursomovil on 27/03/15.
 */
public class EarthquakeDB {

    private EarthquakeOpenHelper helper;
    private SQLiteDatabase db;

    public EarthquakeDB(Context context) {
        this.helper = new EarthquakeOpenHelper(context, EarthquakeOpenHelper.DATABASE_NAME, null, EarthquakeOpenHelper.DATABASE_VERSION);
        this.db = helper.getWritableDatabase();
    }

    public void insertEarthquake() {

    }

    // Clase encargada de gestionar la BD de SQLite
    private static class EarthquakeOpenHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "earthquakes.db";
        private static final String DATABASE_TABLE = "EARTHQUAKES";
        private static final int DATABASE_VERSION = 1;

        // Sentencia SQL para crear nuestra DB
        private static final String DATABASE_CREATE = "CREATE Table " + DATABASE_NAME + "(_id TEXT PRIMARY KEY, place TEXT, magnitude REAL, lat REAL, long REAL, url TEXT, depth REAL, time INTEGER)";

        // Constructor de la clase
        // Crea el objeto Helper para crear, abrir y/o gestionar la BD.
        public EarthquakeOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Método que se ejecutará la primera vez que se cree el objeto
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        // Método que se ejecutará cuando la DB necesite actualizarse
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
