package com.example.hertzfastlane;

/**
 * Created by Steven J on 2/17/2017.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.EstimoteSDK;
import com.estimote.sdk.Nearable;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;
import com.estimote.sdk.cloud.model.Color;
import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.JsonObject;
import com.example.hertzfastlane.estimote.BeaconID;
import com.example.hertzfastlane.estimote.EstimoteCloudBeaconDetails;
import com.example.hertzfastlane.estimote.EstimoteCloudBeaconDetailsFactory;
import com.example.hertzfastlane.estimote.NearableID;
import com.example.hertzfastlane.estimote.ProximityContentManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import static com.estimote.sdk.internal.utils.EstimoteBeacons.ESTIMOTE_PROXIMITY_UUID;
import static com.example.hertzfastlane.MyReservationActivity.convertStreamToString;

//
// Running into any issues? Drop us an email to: contact@estimote.com
//

public class beacons extends AppCompatActivity {

    private static final String TAG = "beacons";

    public static Car getCarData() {
        return carData;
    }

    private static Car carData;

    private static StringBuilder result;

    private Runnable runnable;

    private Object message;

    private BeaconManager beaconManager;

    private static final Region ALL_ESTIMOTE_BEACONS = new Region("rid", ESTIMOTE_PROXIMITY_UUID, null, null);

    private static final Map<Color, Integer> BACKGROUND_COLORS = new HashMap<>();

    static {
        BACKGROUND_COLORS.put(Color.ICY_MARSHMALLOW, android.graphics.Color.rgb(109, 170, 199));
        BACKGROUND_COLORS.put(Color.BLUEBERRY_PIE, android.graphics.Color.rgb(98, 84, 158));
        BACKGROUND_COLORS.put(Color.MINT_COCKTAIL, android.graphics.Color.rgb(155, 186, 160));
    }

    private static final int BACKGROUND_COLOR_NEUTRAL = android.graphics.Color.rgb(160, 169, 172);

    private ProximityContentManager proximityContentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EstimoteSDK.initialize(getApplicationContext(), "stevenjoy99-yahoo-com-s-yo-lyx", "0f4d0fa349ea5d6604f52b776a9653c8");


        Log.d("Tag", "Beacons");
        setContentView(R.layout.activity_main);
        proximityContentManager = new ProximityContentManager(this,
                Arrays.asList(
                        /** Proximity beacons Identifier, minor and major*/
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 32725, 55822),
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 20930, 14720),
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 26788, 12168)), //

                new EstimoteCloudBeaconDetailsFactory());

        /** listener used for nearable stickers*/
        beaconManager = new BeaconManager(getApplicationContext());
        beaconManager.setNearableListener(new BeaconManager.NearableListener() {
            @Override
            public void onNearablesDiscovered(List<Nearable> nearables) {
                //dca0942a7d11f901 Car
                //ead27db3f0fc1775 SHOE
                //9684f729051b8d0d DOOR
                //ec9c2da40aa7394f CHAIR
                /** loops through nearable ID's*/
                for (Nearable nearable : nearables) {
                    NearableID nearableID = new NearableID(nearable.identifier);

                 /** if Id is found do ....*/
                if (nearableID.toString().equals("dca0942a7d11f901")) {
               //     Log.d("Tag", nearableID.toString());

                    /** Loads car class if nearable identification found*/
//                    Runnable runnable = new Runnable(){
//                            @Override
//                            public void run(){
//                                carData = getCarInfo("2012");
//                            }
//                        };
//                        Thread thread = new Thread(runnable);
//                        thread.start();
//
//                        try{
//                            thread.join();
//                        }catch(Exception e){
//                            return;
//                        }
//                    Intent carActivityIntent = new Intent(beacons.this, CarActivity.class);
//                    beacons.this.startActivity(carActivityIntent);
//
//                    beaconManager.disconnect();
                }

                    /** loads help class, for test purposes*/
//                    if (nearableID.toString().equals("9684f729051b8d0d")) {
//                        Intent helper = new Intent(beacons.this, HelpActivity.class);
//                        beacons.this.startActivity(helper);
//                        beaconManager.disconnect();
//
//                    }
                }
                Log.d("TAG1", "Discovered nearables: " + nearables);
                Log.d(TAG, "nearable discovered");
                Log.d(TAG, "size of list is " + String.valueOf(nearables.size()));

            }
        });

        /** Broadcast the nearable beacons signal*/
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override public void onServiceReady() {

                // Beacons ranging.
                beaconManager.startRanging(ALL_ESTIMOTE_BEACONS);

                // Nearable discovery.
                beaconManager.startNearableDiscovery();
            }
        });

       // beaconManager.disconnect();   // Use to disconnect from the signal

        // Proximity iBeacon listener
        proximityContentManager.setListener(new ProximityContentManager.Listener() {
            @Override
            public void onContentChanged(Object content) {
                String text;
                Integer backgroundColor;

                if (content != null ) {
                    EstimoteCloudBeaconDetails beaconDetails = (EstimoteCloudBeaconDetails) content;

                    text = "You're in " + beaconDetails.getBeaconName() + "'s range!";

                    backgroundColor = BACKGROUND_COLORS.get(beaconDetails.getBeaconColor());

                    if (beaconDetails.getBeaconName().equals("ice")) {



                        Runnable runnable = new Runnable(){
                            @Override
                            public void run(){
                                carData = getCarInfo("2012");
                            }
                        };
                        Thread thread = new Thread(runnable);
                        thread.start();

                        try{
                            thread.join();
                        }catch(Exception e){
                            return;
                        }

                       // Intent carActivityIntent = new Intent(beacons.this, CarActivity.class);
                       // beacons.this.startActivity(carActivityIntent);


                    }
                    if (beaconDetails.getBeaconName().equals("blueberry")) {
                        Intent helpActivityIntent = new Intent(beacons.this, HelpActivity.class);
                        beacons.this.startActivity(helpActivityIntent);
                    }
                    if (beaconDetails.getBeaconName().equals("mint")) {
                        goToUrl("http://dallascowboys.com/");
                        //Intent mappyActivityIntent = new Intent(beacons.this, MapActivity.class);
                        //beacons.this.startActivity(mappyActivityIntent);
                    }

                }

                     else {
                        text = "No beacons in range.";
                        backgroundColor = null;
                    }
                 ((TextView) findViewById(R.id.textView)).setText(text);
                findViewById(R.id.relativeLayout).setBackgroundColor(
                        backgroundColor != null ? backgroundColor : BACKGROUND_COLOR_NEUTRAL);
            }
        });
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
            Log.e(TAG, "Can't scan for beacons, some pre-conditions were not met");
            Log.e(TAG, "Read more about what's required at: http://estimote.github.io/Android-SDK/JavaDocs/com/estimote/sdk/SystemRequirementsChecker.html");
            Log.e(TAG, "If this is fixable, you should see a popup on the app's screen right now, asking to enable what's necessary");
        } else {
            Log.d(TAG, "Starting ProximityContentManager content updates");
            proximityContentManager.startContentUpdates();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Stopping ProximityContentManager content updates");
        proximityContentManager.stopContentUpdates();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        proximityContentManager.destroy();
    }


    private Car getCarInfo(String car_id){
        Car carInformation = null;
        URL url = null;
        try {
            url = new URL("https://q3igdv3op1.execute-api.us-east-1.amazonaws.com/prod/readingFleet?car_id="
                    + car_id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            result = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                result.append(line);
            }
            String resultString = result.toString();

            JSONObject carMap = new JSONObject(resultString);

            JSONObject car = carMap.getJSONObject("Item");
            JSONObject carInfo = car.getJSONObject("info");

//Deserializing to JSON Car Information
            ObjectMapper mapper = new ObjectMapper();

            carInformation = mapper.readValue(car.toString(),Car.class);

            Info infoCar = mapper.readValue(carInfo.toString(),Info.class);
            carInformation.setInfo(infoCar);

        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e1){
            e1.printStackTrace();
        }

        return carInformation;
    }


}