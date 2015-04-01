package com.aitorzubizarreta.earthquakes.task;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aitorzubizarreta.earthquakes.R;
import com.aitorzubizarreta.earthquakes.database.EarthquakeDB;
import com.aitorzubizarreta.earthquakes.model.Coordinate;
import com.aitorzubizarreta.earthquakes.model.EarthQuake;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by cursomovil on 25/03/15.
 */
public class DownloadEarthquakesTask extends AsyncTask<String, EarthQuake, Integer> {
    private static final String TAG = "EARTHQUAKE";
    private static final String EARTHQUEAKE = "EARTHQUAKE";

    private EarthquakeDB earthquakeDB;



    public interface AddEarthQuakeInterface {
        //public void AddEarthQuake(EarthQuake earthquake);
        public void notifyTotal(Integer count);
    }

    private AddEarthQuakeInterface target;

    public DownloadEarthquakesTask(Context context, AddEarthQuakeInterface target) {
        this.target = target;
        earthquakeDB = new EarthquakeDB(context);
    }

    @Override
    protected Integer doInBackground(String... urls) {
        int count = 0;
        if (urls.length > 0) {
            //count = updateEarthquakes(urls[0]);
        }
        return count;
    }

    @Override
    protected void onProgressUpdate(EarthQuake... earthquakes) {
        super.onProgressUpdate(earthquakes);

        //target.AddEarthQuake(earthquakes[0]);
    }

    @Override
    protected void onPostExecute(Integer count) {
        super.onPostExecute(count);
        target.notifyTotal(count);
    }


}
