package com.aitorzubizarreta.earthquakes.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
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

public class DownloadEarthquakesService extends Service {

    // El servicio se ejecuta en segundo plano pero en el mismo thread
    // Si queremos que haga algo como descargar de internet o DB tendremos que ejecutar esas funciones en otros threads

    private EarthquakeDB earthquakeDB;
    private static final String EARTHQUEAKE = "EARTHQUAKE";

    @Override
    public void onCreate() {
        super.onCreate();
        earthquakeDB = new EarthquakeDB(this);
    }

    private Integer updateEarthquakes(String url1) {

        JSONObject json;
        String earthquakesFeed = url1;
        Integer count = 0;

        try {
            URL url = new URL(earthquakesFeed);

            //	Create a new HTTP URL connection
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection)connection;

            int	responseCode = httpConnection.getResponseCode();
            if	(responseCode == HttpURLConnection.HTTP_OK)	{
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                json = new JSONObject(responseStrBuilder.toString());
                JSONArray earthquakes = json.getJSONArray("features");

                count = earthquakes.length();

                for (int i = earthquakes.length()-1; i >= 0; i--) {
                    processEarthQuakeTask(earthquakes.getJSONObject(i));
                }
            }
        } catch (MalformedURLException e) {
            Log.d(EARTHQUEAKE, "Malformed	URL	Exception.", e);
        } catch (IOException e) {
            Log.d(EARTHQUEAKE, "IO	Exception.", e);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return count;
    }

    private void processEarthQuakeTask(JSONObject jsonObject) {
        try {
            // Obtenemos las coordenadas
            JSONArray jsonCoords = jsonObject.getJSONObject("geometry").getJSONArray("coordinates");
            Coordinate coords = new Coordinate(jsonCoords.getDouble(0), jsonCoords.getDouble(1), jsonCoords.getDouble(2));

            String id = jsonObject.getString("id");
            JSONObject properties = jsonObject.getJSONObject("properties");
            EarthQuake earthquake = new EarthQuake();
            earthquake.set_id(jsonObject.getString("id"));
            earthquake.setPlace(properties.getString("place"));
            earthquake.setMagnitude(properties.getDouble("mag"));
            earthquake.setTime(properties.getLong("time"));
            earthquake.setUrl(properties.getString("url"));
            earthquake.setCoords(coords);

            Log.d(EARTHQUEAKE, earthquake.toString());
            Log.d(EARTHQUEAKE, "Magnitude " + earthquake.getMagnitude());
            Log.d(EARTHQUEAKE, "Place " + earthquake.getPlace());
            Log.d(EARTHQUEAKE, "Time " + earthquake.getTime());

            //publishProgress(earthquake); // onProgressUpdate

            // Guardar los datos en la BD
            earthquakeDB.insertEarthquake(earthquake);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        // Creamos un thread para poder ejecutar el servicio porque, el servicio se conecta a internet y android no permite hacer esto en el thread principal
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                updateEarthquakes(getString(R.string.earthquakes_url));
            }
        });

        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
