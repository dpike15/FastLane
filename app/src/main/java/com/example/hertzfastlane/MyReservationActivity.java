package com.example.hertzfastlane;


import android.content.Entity;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.apache.http.Header;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;


import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;


import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;



public class MyReservationActivity extends AppCompatActivity {
    private Car car;
    Member member;
    private static String result ="";
    public static final String URL = "https://d9c29c15-ac06-4a7a-83f6-00e3cd315b1c-bluemix:40448ad9e7403f7b1d2b76e312f1673801f8011aeba32256ff860596465bd17b@d9c29c15-ac06-4a7a-83f6-00e3cd315b1c-bluemix.cloudant.com/reservations/_find";
    private static StringEntity entity;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservation);

        //Getting user information from login
        member = LoginActivity.getMember();



        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                String selectorRes = "{\"selector\": {\"Customer_Id\": \"0\"}}";

                HttpClient httpclient = new DefaultHttpClient();


                HttpPost request = new HttpPost(URL);

                try {
                    entity = new StringEntity(selectorRes);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                request.setEntity(entity);
                request.setHeader("Accept", "application/json");
                request.setHeader("Content-type", "application/json");

                HttpResponse response;


                try {
                    response = httpclient.execute(request);
                    HttpEntity entity = response.getEntity();
                    InputStream instream = entity.getContent();
                    String result = convertStreamToString(instream);
                    JSONObject json = new JSONObject(result);
                    JSONArray array = json.getJSONArray("docs");
                    JSONObject jsonRes = (JSONObject) array.get(0);
                    String car_vin = jsonRes.get("Car_Vin").toString();

                    instream.close();


                    String URLGET = "https://d9c29c15-ac06-4a7a-83f6-00e3cd315b1c-bluemix:40448ad9e7403f7b1d2b76e312f1673801f8011aeba32256ff860596465bd17b@d9c29c15-ac06-4a7a-83f6-00e3cd315b1c-bluemix.cloudant.com/cars/" + car_vin;

                    HttpGet get = new HttpGet(URLGET);

                    HttpResponse responseCar = httpclient.execute(get);
                    HttpEntity entityCar = responseCar.getEntity();
                    InputStream instreamCar = entityCar.getContent();
                    String resultCar = convertStreamToString(instreamCar);
                    JSONObject cars = new JSONObject(resultCar);
                    JSONObject info = cars.getJSONObject("info");
                    String infoString = info.toString();
                    instream.close();

                    ObjectMapper mapper = new ObjectMapper();

                    car = mapper.readValue(resultCar, Car.class);

                    Info infoCar = mapper.readValue(infoString,Info.class);
                    car.setInfo(infoCar);



                } catch (Exception e) {
                    e.printStackTrace();

                }


            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        try {
            thread.join();
        } catch (Exception e) {
            return;
        }
        //Setting textViews with Dynamic Data
        TextView carTitle = (TextView) findViewById(R.id.textView);
        carTitle.setText(car.getInfo().getYear() + " " + car.getInfo().getModel() + " " + car.getInfo().getMake());


        TextView carMpg = (TextView) findViewById(R.id.textView2);
        carMpg.setText("MPG: City: " + car.getInfo().getMpgCity() + " Hwy: " + car.getInfo().getMpgHighway());

        TextView passengers = (TextView) findViewById(R.id.textView3);
        passengers.setText("Passengers: " + car.getInfo().getPassengers());

        TextView luggage = (TextView) findViewById(R.id.textView4);
        luggage.setText("Luggage: " + car.getInfo().getLuggage());

        TextView trasnmission = (TextView) findViewById(R.id.textView5);
        trasnmission.setText("Transmission: " + car.getInfo().getTransmission());

        TextView featuresText = (TextView) findViewById(R.id.textView6);
        featuresText.setText(car.getFeatures().get(0));

        TextView rate = (TextView) findViewById(R.id.textView7);
        rate.setText("Rate: " + car.getInfo().getRate() + " Daily");


        Button upgradeButton = (Button) findViewById(R.id.button);
        upgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS("8605978596", "Testing message");
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public static Drawable LoadImage(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "SrcName");

            return d;
        } catch (Exception e) {
            return null;
        }
    }

    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("MyReservation Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private static String convertStreamToString(InputStream is) {
	    /*
	     * To convert the InputStream to String we use the BufferedReader.readLine()
	     * method. We iterate until the BufferedReader return null which means
	     * there's no more data to read. Each line will appended to a StringBuilder
	     * and returned as String.
	     */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
