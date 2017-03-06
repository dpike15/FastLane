package com.example.hertzfastlane;

/**
 * Created by Steven J on 2/17/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.EstimoteSDK;
import com.estimote.sdk.Nearable;
import com.estimote.sdk.Region;
import com.estimote.sdk.SecureRegion;
import com.estimote.sdk.SystemRequirementsChecker;
import com.estimote.sdk.cloud.model.Color;
import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.JsonObject;
import com.example.hertzfastlane.estimote.BeaconID;
import com.example.hertzfastlane.estimote.EstimoteCloudBeaconDetails;
import com.example.hertzfastlane.estimote.EstimoteCloudBeaconDetailsFactory;
import com.example.hertzfastlane.estimote.NearableID;
import com.example.hertzfastlane.estimote.ProximityContentManager;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import static com.estimote.sdk.internal.utils.EstimoteBeacons.ESTIMOTE_PROXIMITY_UUID;

public class beacons extends AppCompatActivity {

    private static final String TAG = "beacons";

    public static Car getCarData() {
        return carData;
    }

    private static Car carData;

    private List<TestingCar> mCars;

    private List<String> carIds;


    private static StringBuilder result;

    private Runnable runnable;


    private BeaconManager beaconManager;

    TestingCarAdapter adapter;

    private SecureRegion secureRegion;
    private SecureRegion secureRegion2;


    private static final Region ALL_ESTIMOTE_BEACONS = new Region("rid", ESTIMOTE_PROXIMITY_UUID, null, null);

    private static final Map<Color, Integer> BACKGROUND_COLORS = new HashMap<>();

    static {
        BACKGROUND_COLORS.put(Color.ICY_MARSHMALLOW, android.graphics.Color.rgb(109, 170, 199));
        BACKGROUND_COLORS.put(Color.BLUEBERRY_PIE, android.graphics.Color.rgb(98, 84, 158));
        BACKGROUND_COLORS.put(Color.MINT_COCKTAIL, android.graphics.Color.rgb(155, 186, 160));
    }

    private static final int BACKGROUND_COLOR_NEUTRAL = android.graphics.Color.rgb(160, 169, 172);

    private ProximityContentManager proximityContentManager;

    static Map <String, String> nearableMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EstimoteSDK.initialize(getApplicationContext(), "stevenjoy99-yahoo-com-s-yo-lyx", "0f4d0fa349ea5d6604f52b776a9653c8");

        nearableMap = new HashMap<String,String>();
        setContentView(R.layout.car_selection);
        Context context = this;

        RecyclerView rvTestingCars = (RecyclerView) findViewById(R.id.rvTestingCar);
        rvTestingCars.setLayoutManager(new LinearLayoutManager(this));


        mCars= new ArrayList<>();
        adapter = new TestingCarAdapter(mCars);
        rvTestingCars.setAdapter(adapter);


        // use for proximity beacons STOP RANGING
        secureRegion = new SecureRegion("Secure region",UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),20930, 14720);
        secureRegion2 = new SecureRegion("Secure region",UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),26788, 12168);

        Log.d("Tag", "Beacons");
        proximityContentManager = new ProximityContentManager(this,
                Arrays.asList(
                        /** Proximity beacons Identifier, minor and major*/
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 32725, 55822),
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 20930, 14720),
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 26788, 12168)), //

                new EstimoteCloudBeaconDetailsFactory());

        /** listener used for nearable stickers*/
        beaconManager = new BeaconManager(getApplicationContext());

        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override public void onBeaconsDiscovered(Region region, final List beacons) {
               // Log.d("TAG2", "Ranged beacons: " + beacons);
            }
        });


        beaconManager.setNearableListener(new BeaconManager.NearableListener() {
            @Override
            public void onNearablesDiscovered(List<Nearable> nearables) {

                //dca0942a7d11f901 Car
                //ead27db3f0fc1775 SHOE
                //9684f729051b8d0d DOOR
                //ec9c2da40aa7394f CHAIR
                int numberScanned = 0;
                /** loops through nearable ID's*/
                for (Nearable nearable : nearables) {
                    if (!nearableMap.containsKey(nearable.identifier)) {

                        NearableID nearableID = new NearableID(nearable.identifier);

                        nearableMap.put(nearable.identifier, nearable.identifier);
                        for (String key : nearableMap.keySet()) {
                            Log.d("hashkey", key);
                        }

                        if ((nearable.identifier.contains("dca0942a7d11f901"))) {
                            mCars.add(0, new TestingCar("Tesla", "P100 (Fridge)", "2017", "$89.99"));
                            adapter.notifyItemInserted(0);
                        } else if ((nearable.identifier.contains("ec9c2da40aa7394"))) {
                            mCars.add(0, new TestingCar("BWM", "M5 (Dog)", "2017", "$99.99"));
                            adapter.notifyItemInserted(0);
                        } else if ((nearable.identifier.contains("9684f729051b8d0d"))) {
                            mCars.add(0, new TestingCar("Derek's", "Hoopty (Blank)", "1999", "$Free.99"));
                            adapter.notifyItemInserted(0);
                        }


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
                        //}


                    }
                }

                Log.d("TAG1", "Discovered nearables: " + nearables);
                Log.d(TAG, "nearable discovered");
                Log.d(TAG, "size of list is " + String.valueOf(nearables.size()));

            }

        });



        /** Broadcast the nearable beacons signal*/
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {

                // Beacons ranging.
                beaconManager.startRanging(ALL_ESTIMOTE_BEACONS);

                // Nearable discovery.
                beaconManager.startNearableDiscovery();

            }
        });


        proximityContentManager.setListener(new ProximityContentManager.Listener() {
           @Override
            public void onContentChanged(Object content) {
//                String text;
//                Integer backgroundColor;
//
               if (content != null) {
                    EstimoteCloudBeaconDetails beaconDetails = (EstimoteCloudBeaconDetails) content;
//
//                    text = "You're in " + beaconDetails.getBeaconName() + "'s range!";
//
//                    backgroundColor = BACKGROUND_COLORS.get(beaconDetails.getBeaconColor());
//
//                    if (beaconDetails.getBeaconName().equals("ice")) {
//                            TestingCar car = new TestingCar("BWM", "M5 (Dog)", "2017", "$99.99");
//                            String id = car.getCar_id();
//                        if (!carsMap.containsKey(id)) {
//                            /*
//                            carIds.add(car.getCar_id());
//                            mCars.add(car);
//                            adapter.notifyItemInserted(adapter.getItemCount());
//                            int size = mCars.size();
//                            Log.d("FAG", "mCarsSize: " + size);
//                            */
//                            carsMap.put(id, car);
//                            adapter.notifyItemInserted(adapter.getItemCount());
//                            int size = carsMap.size();
//                            Log.d("FAG", "mCarsSize: " + size);
//                        }
//                      mCars.clear();
//                        //adapter.notify();
//
//                        beaconManager.stopMonitoring(secureRegion);
//                        //break;
//
//                       int index = adapter.getItemCount();
//                       mCars.add(0, new TestingCar("BWM", "M5 (Dog)", "2017", "$99.99"));
//                       adapter.notifyItemInserted(0);
//                   }

//                   if (beaconDetails.getBeaconName().equals("mint")) {
//                       TestingCar car = new TestingCar("Mercedes", "A33 (CAT)", "2014", "$95.99");
//                      /*
//                       if (!carIds.contains(car.getCar_id())) {
//                           carIds.add(car.getCar_id());
//                           mCars.add(car);
//                           adapter.notifyItemInserted(adapter.getItemCount());
//                           int size = mCars.size();
//                           Log.d("FAG", "mCarsSize: " + size);
//                        */
//                       String id = car.getCar_id();
//                       if(!carsMap.containsKey(id)){
//                           carsMap.put(id, car);
//                           adapter.notifyItemInserted(adapter.getItemCount());
//                           int size = carsMap.size();
//                           Log.d("FAG", "mCarsSize: " + size);
//                       }
//
//                       int index = adapter.getItemCount();
//                       mCars.add(0, new TestingCar("Audi", "A4 (Dolphin)", "2015", "$89.99"));
//                       adapter.notifyItemInserted(0);
//                   }


                } else {
//                         text = "No beacons in range.";
//                         backgroundColor = null;
               }
//                //((TextView) findViewById(R.id.textView)).setText(text);
//                //findViewById(R.id.relativeLayout).setBackgroundColor(
//                      //  backgroundColor != null ? backgroundColor : BACKGROUND_COLOR_NEUTRAL);
            }
        });

    }

    private void goToUrl(String url) {
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
        beaconManager.disconnect();
    }


    private Car getCarInfo(String car_id) {
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
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String resultString = result.toString();

            JSONObject carMap = new JSONObject(resultString);

            JSONObject car = carMap.getJSONObject("Item");
            JSONObject carInfo = car.getJSONObject("info");

            //Deserializing to JSON Car Information
            ObjectMapper mapper = new ObjectMapper();

            carInformation = mapper.readValue(car.toString(), Car.class);

            Info infoCar = mapper.readValue(carInfo.toString(), Info.class);
            carInformation.setInfo(infoCar);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return carInformation;
    }


}