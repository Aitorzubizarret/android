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
            count = updateEarthquakes(urls[0]);
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
            Log.d(TAG, "Malformed	URL	Exception.", e);
        } catch (IOException e) {
            Log.d(TAG, "IO	Exception.", e);
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

            publishProgress(earthquake); // onProgressUpdate

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
