package com.example.hertzfastlane;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
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

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MyReservationActivity extends AppCompatActivity {
    private Member member;
    private ProgressBar spinner;
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
                member = APICalls.getUser();

                Reservation memberReservation = null;
                try {
                    memberReservation = getReservation(member);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                    //Setting textViews with Dynamic Data

                    //Car name
                    //TextView carTitle = (TextView) findViewById(R.id.tvVehicleMakeModel);
                    //carTitle.setText(car.getInfo().getYear() + " " + car.getInfo().getModel() + " " + car.getInfo().getMake());

                    //Confirmation Number
                    TextView confirmation = (TextView) findViewById(R.id.tvConfirmationNumber);
                    //confirmation.setText((memberReservation.getReservation_id()));

                    //Pick up Location
                    TextView pickupLocation = (TextView) findViewById(R.id.tvPickUpLocation);
                    //pickupLocation.setText(memberReservation.getPick_Up());

                    //Return Location
                    TextView returnLocation = (TextView) findViewById(R.id.tvReturnLocation);
                    //returnLocation.setText(memberReservation.getReturnLocation());

                    //Reservation Date
                    TextView reservationDate = (TextView) findViewById(R.id.tvReservationDate);
                    //reservationDate.setText("Reservation Date: " + memberReservation.getPick_Up_Date());





                }
        };  //END RUNNABLE

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




        Button bSelectVehicle = (Button) findViewById(R.id.bSelectVehicle);
        bSelectVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                //sendSMS("8605978596", "Testing message");
//                Intent scanIntent = new Intent(MyReservationActivity.this, QrScanner.class);
//                MyReservationActivity.this.startActivity(scanIntent);
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

//    public static Drawable LoadImage(String url) {
//        try {
//            InputStream is = (InputStream) new URL(url).getContent();
//            Drawable d = Drawable.createFromStream(is, "SrcName");
//
//            return d;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    private void sendSMS(String phoneNumber, String message) {
//        SmsManager sms = SmsManager.getDefault();
//        sms.sendTextMessage(phoneNumber, null, message, null, null);
//    }

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

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_maps:
//                startActivity(new Intent(this, MapActivity.class));
//                return true;
//            case R.id.action_help:
//                startActivity(new Intent(this, HelpActivity.class));
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

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

    private Reservation getReservation(Member member) throws JSONException, IOException {
        StringBuilder result = null;
        URL url = null;
        try {
            url = new URL("https://q3igdv3op1.execute-api.us-east-1.amazonaws.com/prod/gate?mem_id="
                    + member.getMember_id());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection urlConnection = null;

            urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String resultString = result.toString();
            Log.d("TAGGY", result.toString());

            JSONObject resMap = new JSONObject(resultString);

            JSONArray reses = resMap.getJSONArray("Items");
            JSONObject res = reses.getJSONObject(0);

            //Deserializing to JSON Car Information
            ObjectMapper mapper = new ObjectMapper();

            Reservation reservation  = mapper.readValue(res.toString(), Reservation.class);

            return reservation;





    }

//    private Car getCarInfo(String car_id) {
//        StringBuilder result = null;
//        Car carInformation = null;
//        URL url = null;
//        try {
//            url = new URL("https://q3igdv3op1.execute-api.us-east-1.amazonaws.com/prod/readingFleet?car_id="
//                    + car_id);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        HttpURLConnection urlConnection = null;
//        try {
//            urlConnection = (HttpURLConnection) url.openConnection();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
//            result = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                result.append(line);
//            }
//            String resultString = result.toString();
//
//            JSONObject carMap = new JSONObject(resultString);
//
//            JSONObject car = carMap.getJSONObject("Item");
//            JSONObject carInfo = car.getJSONObject("info");
//
//            //Deserializing to JSON Car Information
//            ObjectMapper mapper = new ObjectMapper();
//
//            carInformation = mapper.readValue(car.toString(), Car.class);
//
//            Info infoCar = mapper.readValue(carInfo.toString(), Info.class);
//            carInformation.setInfo(infoCar);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e1) {
//            e1.printStackTrace();
//        }
//
//        return carInformation;
//    }

//    public static String convertStreamToString(InputStream is) {
//	    /*
//	     * To convert the InputStream to String we use the BufferedReader.readLine()
//	     * method. We iterate until the BufferedReader return null which means
//	     * there's no more data to read. Each line will appended to a StringBuilder
//	     * and returned as String.
//	     */
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//        StringBuilder sb = new StringBuilder();
//
//        String line = null;
//        try {
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return sb.toString();
//    }
}
