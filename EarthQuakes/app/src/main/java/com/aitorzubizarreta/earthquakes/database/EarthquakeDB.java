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
        this.helper = new EarthquakeOpenHelper(context, EarthquakeOpenHelper.DATABASE_NAME, 0, EarthquakeOpenHelper.DATABASE_VERSION);
        this.db = helper.getWritableDatabase();
    }

    private static class EarthquakeOpenHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "earthquakes.db";
        private static final String DATABASE_TABLE = "EARTHQUAKES";
        private static final int DATABASE_VERSION = 1;

        // SQL statement to create a DB
        private static final String DATABASE_CREATE = "CREATE TABLE " + DATABASE_NAME + "(_id TEXT PRIMARY KEY, place TEXT, magnitude REAL, lat REAL, long REAL, url TEXT, time INTEGER)";

        public EarthquakeOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
            super(context, name, factory, version, errorHandler);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
