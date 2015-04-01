package com.aitorzubizarreta.earthquakes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.aitorzubizarreta.earthquakes.model.EarthQuake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cursomovil on 27/03/15.
 */
public class EarthquakeDB {

    private static String CONSOLE = "consola";
    private EarthquakeOpenHelper helper;
    private SQLiteDatabase db;

    // Definir las tablas de las columnas de la BD
    public static final String KEY_ID = "_id";
    public static final String KEY_PLACE = "place";
    public static final String KEY_MAGNITUDE = "magnitude";
    public static final String KEY_LAT = "lat";
    public static final String KEY_LONG = "long";
    public static final String KEY_URL = "url";
    public static final String KEY_DEPTH = "depth";
    public static final String KEY_TIME = "time";

    public static final String[] ALL_COLUMNS = {
            KEY_ID,
            KEY_PLACE,
            KEY_MAGNITUDE,
            KEY_LAT,
            KEY_LONG,
            KEY_URL,
            KEY_DEPTH,
            KEY_TIME
    };

    public EarthquakeDB(Context context) {
        this.helper = new EarthquakeOpenHelper(context, EarthquakeOpenHelper.DATABASE_NAME, null, EarthquakeOpenHelper.DATABASE_VERSION);
        this.db = helper.getWritableDatabase();
    }

    public void insertEarthquake(EarthQuake earthQuake) {
        ContentValues terremoto = new ContentValues();
        terremoto.put(KEY_ID, earthQuake.get_id());
        terremoto.put(KEY_PLACE, earthQuake.getPlace());
        terremoto.put(KEY_MAGNITUDE, earthQuake.getMagnitude());
        terremoto.put(KEY_LAT, earthQuake.getCoords().getLat());
        terremoto.put(KEY_LONG, earthQuake.getCoords().getLng());
        terremoto.put(KEY_URL, earthQuake.getUrl());
        terremoto.put(KEY_DEPTH, earthQuake.getCoords().getDepth());
        terremoto.put(KEY_TIME, earthQuake.getTime().getTime());

        try {
            db.insertOrThrow(EarthquakeOpenHelper.DATABASE_TABLE, null, terremoto);
        } catch (Exception e)  {
            Log.d(CONSOLE, "Error en la inserción de DB");
        }
    }

    private List<EarthQuake> query(String where, String[] whereArgs) {
        List<EarthQuake> earthQuakes = new ArrayList<>();
        Cursor cursor = db.query(
                EarthquakeOpenHelper.DATABASE_TABLE,
                ALL_COLUMNS,
                where,
                whereArgs,
                null,
                null,
                KEY_TIME + " DESC"
        );

        HashMap<String, Integer> indexes = new HashMap<>();
        for (int i = 0; i > ALL_COLUMNS.length; i++) {
            indexes.put(ALL_COLUMNS[i], cursor.getColumnIndex(ALL_COLUMNS[i]));
        }

        while(cursor.moveToNext()) {
            EarthQuake earthQuake = new EarthQuake();
            earthQuake.set_id(cursor.getString(indexes.get(KEY_ID)));
            earthQuake.setPlace(cursor.getString(indexes.get(KEY_PLACE)));
           //earthQuake.setCoords(cursor.getDouble(indexes.get(KEY_LAT)));
            //earthQuake.set(cursor.getDouble(indexes.get(KEY_LONG)));
            //earthQuake.set_id(cursor.getDouble(indexes.get(KEY_DEPTH)));
            earthQuake.setMagnitude(cursor.getDouble(indexes.get(KEY_MAGNITUDE)));
            earthQuake.setTime(cursor.getLong(indexes.get(KEY_TIME)));
            earthQuake.setUrl(cursor.getString(indexes.get(KEY_URL)));
        }
        return earthQuakes;
    }

    // Clase encargada de gestionar la BD de SQLite
    private static class EarthquakeOpenHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "earthquakes.db";
        private static final String DATABASE_TABLE = "EARTHQUAKES";
        private static final int DATABASE_VERSION = 1;

        // Sentencia SQL para crear nuestra DB
        private static final String DATABASE_CREATE = "CREATE Table " + DATABASE_TABLE + "(_id TEXT PRIMARY KEY, place TEXT, magnitude REAL, lat REAL, long REAL, url TEXT, depth REAL, time INTEGER)";

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
