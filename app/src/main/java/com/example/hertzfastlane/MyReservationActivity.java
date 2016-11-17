package com.example.hertzfastlane;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MyReservationActivity extends AppCompatActivity {
    private Car car;
    private Member member;
    private static String result ="";
    public static final String URL = "https://cad91ce6-3bd7-475a-97ed-7fb3dfe82486-bluemix.cloudant.com/reservations/_find";
    private static StringEntity entity;
    private Reservation memberReservation;
    private ProgressBar spinner;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservation);

        spinner =(ProgressBar)findViewById(R.id.progress_loader);
        spinner.setVisibility(View.GONE);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Getting user information from login




        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                member = LoginActivity.getMember();
                //JSON Query Paramaters
                String selectorRes = "{\"selector\": {\"customer_Id\": \"" + member.getCustomer_id() + "\"}}";

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

                //Get RESERVATION INFORMATION
                try {
                    response = httpclient.execute(request);
                    HttpEntity entity = response.getEntity();
                    InputStream instream = entity.getContent();
                    //JSON RESPONSE AS STRING
                    String result = convertStreamToString(instream);
                    JSONObject json = new JSONObject(result);
                    //RETURNED AS ARRAY OF DOCUMENTS TITLED DOCS
                    JSONArray array = json.getJSONArray("docs");
                    JSONObject jsonRes = (JSONObject) array.get(0);
                    String reservation = jsonRes.toString();
                    ObjectMapper mapperReservation = new ObjectMapper();
                    //PULL CAR VIN FROM RESERVATION NUMBER
                    memberReservation = mapperReservation.readValue(reservation, Reservation.class);
                    instream.close();

                    //URL CALL FOR CARS DATABASE
                    String URLGET = "https://cad91ce6-3bd7-475a-97ed-7fb3dfe82486-bluemix.cloudant.com/cars/"
                            + memberReservation.getCar_Vin();

                    HttpGet get = new HttpGet(URLGET);

                    //Web Service
                    HttpResponse responseCar = httpclient.execute(get);
                    HttpEntity entityCar = responseCar.getEntity();
                    InputStream instreamCar = entityCar.getContent();
                    String resultCar = convertStreamToString(instreamCar);

                    //Deserializing
                    JSONObject cars = new JSONObject(resultCar);
                    JSONObject info = cars.getJSONObject("info");
                    String infoString = info.toString();
                    instreamCar.close();

                    //Deserializing to JSON Car Information
                    ObjectMapper mapper = new ObjectMapper();

                    car = mapper.readValue(resultCar, Car.class);

                    Info infoCar = mapper.readValue(infoString,Info.class);
                    car.setInfo(infoCar);

                    //Setting textViews with Dynamic Data

                    //Car name
                    //TextView carTitle = (TextView) findViewById(R.id.tvVehicleMakeModel);
                    //carTitle.setText(car.getInfo().getYear() + " " + car.getInfo().getModel() + " " + car.getInfo().getMake());

                    //Confirmation Number
                    TextView confirmation = (TextView) findViewById(R.id.tvConfirmationNumber);
                    confirmation.setText((memberReservation.getReservation_Num()));

                    //Pick up Location
                    TextView pickupLocation = (TextView) findViewById(R.id.tvPickUpLocation);
                    pickupLocation.setText(memberReservation.getPick_Up());

                    //Return Location
                    TextView returnLocation = (TextView) findViewById(R.id.tvReturnLocation);
                    returnLocation.setText(memberReservation.getReturnLocation());

                    //Reservation Date
                    TextView reservationDate = (TextView) findViewById(R.id.tvReservationDate);
                    reservationDate.setText("Reservation Date: " + memberReservation.getPick_Up_Date());

                } catch (Exception e) {
                    e.printStackTrace();

                }


            }
        };

  /*      ImageView carImage=(ImageView)findViewById(vehicleImage);
        Picasso.with(getApplicationContext())
                .load("http://i.imgur.com/OYMyYcj.png")
//.resize(283,113)
                .error(R.drawable.app_icon)
                .into(carImage);
*/

//        ImageView carImage=(ImageView)findViewById(vehicleImage);
//        Picasso.with(getApplicationContext())
//                .load("http://i.imgur.com/OYMyYcj.png")
////.resize(283,113)
//                .error(R.drawable.app_icon)
//                .into(carImage);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.scheduleAtFixedRate(runnable , 0, 600, TimeUnit.MILLISECONDS );


        Thread thread = new Thread(runnable);
        thread.start();

        try {
            thread.join();
        } catch (Exception e) {
            return;
        }




        Button bScanVehicle = (Button) findViewById(R.id.bScanVehicle);
        bScanVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                //sendSMS("8605978596", "Testing message");
                Intent scanIntent = new Intent(MyReservationActivity.this, QrScanner.class);
                MyReservationActivity.this.startActivity(scanIntent);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_maps:
                startActivity(new Intent(this, MapActivity.class));
                return true;
            case R.id.action_help:
                startActivity(new Intent(this, HelpActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    protected void onRestart() {
        super.onRestart();
        spinner.setVisibility(View.GONE);
    }

    public static String convertStreamToString(InputStream is) {
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
