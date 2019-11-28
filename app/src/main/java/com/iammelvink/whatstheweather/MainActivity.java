package com.iammelvink.whatstheweather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    /*Class Fields/variables*/
    private EditText txtCity;
    private TextView txtWeather;

    public void getWeatherNow(View view) {
        String searchCity = txtCity.getText().toString();

        /*Downloading text/html from this link*/
        DownloadTask task = new DownloadTask();
        String result = null;
        try {
            result = task.execute("http://api.openweathermap.org/data/2.5/weather?q=" + searchCity
                    + "&APPID=YOUR_API_KEY").get();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
        Log.i("Result", result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Connecting UI elements to code*/
        txtCity = findViewById(R.id.txtCity);
        txtWeather = findViewById(R.id.txtWeather);

    }

    /*Class download text/html on a background thread*/
    public class DownloadTask extends AsyncTask<String, Void, String> {

        /*array thingie
         * String... strings*/
        @Override
        protected String doInBackground(String... urls) {
            /*Return when task is complete*/
            String result = "";

            /*Converts String to URL*/
            URL url;
            /*Creates connection*/
            HttpURLConnection urlConnection = null;

            /*Converting String to URL*/
            try {
                url = new URL(urls[0]);
                /*Creating connection
                 * CAST URL AS HttpURLConnection*/
                urlConnection = (HttpURLConnection) url.openConnection();

                /*Gather data as in comes in*/
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                /*Each character is saved as an int*/
                int data = reader.read();

                /*
                Runs while there is something to download
                -1 means nothing*/
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                /*Downloaded text/html*/
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                return "Failed";
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                return "Failed";
            }
        }

        /*While downloading
         * do this*/
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                /*JSON object*/
                JSONObject jsonObject = new JSONObject(s);

                /*Saved JSON data to String*/
                String weatherInfo = jsonObject.getString("weather");
                Log.i("Weather content", weatherInfo);

                /*JSONArray weather*/
                JSONArray weatherArray = new JSONArray(weatherInfo);
                JSONObject part0;

                /*weather*/
                String main = "Condition: ";
                String description = "Description: ";

                /*Getting all info about the weather*/
                for (int i = 0; i < weatherArray.length(); i++) {
                    part0 = weatherArray.getJSONObject(i);
                    Log.i("Main", part0.getString("main"));
                    Log.i("Description", part0.getString("description"));

                    main += part0.getString("main");
                    description += part0.getString("description");

                }
                String thisWeather = main + "\n"
                        + description;
                txtWeather.setText(thisWeather);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }


            Log.i("JSON", s);
        }
    }
}
