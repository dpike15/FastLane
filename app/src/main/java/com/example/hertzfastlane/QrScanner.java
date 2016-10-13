package com.example.hertzfastlane;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.Result;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**

 /**
 * Created by Steven J on 9/22/2016.
 */
public class QrScanner extends Activity implements ZXingScannerView.ResultHandler {
    private String qrString;
    private boolean scanResult;
    private ZXingScannerView mScannerView;
    private final String URL = "https://d9c29c15-ac06-4a7a-83f6-00e3cd315b1c-bluemix:40448ad9e7403f7b1d2b76e312f1673801f8011aeba32256ff860596465bd17b@d9c29c15-ac06-4a7a-83f6-00e3cd315b1c-bluemix.cloudant.com/cars/";
    private Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        // Get the Camera instance as the activity achieves full user focus
        if (mScannerView == null) {
            mScannerView.startCamera(); // Local method to handle camera init
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        //Do anything with result here :D
        Log.w("handleResult", result.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        qrString = result.getText();

        //Intiialize result
        scanResult = false;

        Runnable runnable = new Runnable(){

            @Override
            public void run(){
                member = LoginActivity.getMember();

                HttpClient client = new DefaultHttpClient();

                //Getting Car Information From Database
                HttpGet get = new HttpGet(URL + qrString);

                HttpResponse response ;

                try{
                    response = client.execute(get);

                    HttpEntity entity = response.getEntity();
                    InputStream instreamCar = entity.getContent();
                    String resultCar = MyReservationActivity.convertStreamToString(instreamCar);
                    JSONObject carJson = new JSONObject(resultCar);
                    String vehicleClass = carJson.get("vehicleClass").toString();



                    String reservationURL = "https://d9c29c15-ac06-4a7a-83f6-00e3cd315b1c-bluemix:40448ad9e7403f7b1d2b76e312f1673801f8011aeba32256ff860596465bd17b@d9c29c15-ac06-4a7a-83f6-00e3cd315b1c-bluemix.cloudant.com/reservations/_find";
                    //Getting Reservation Data
                    String selectorRes = "{\"selector\": {\"customer_Id\": \"" + member.getCustomer_id() + "\"}}";

                    HttpClient httpclient = new DefaultHttpClient();

                    HttpPost request = new HttpPost(reservationURL);

                    entity = new StringEntity(selectorRes);

                    request.setEntity(entity);
                    request.setHeader("Accept", "application/json");
                    request.setHeader("Content-type", "application/json");

                    //Get RESERVATION INFORMATION

                        response = httpclient.execute(request);
                        HttpEntity resEntity = response.getEntity();
                        InputStream instream = resEntity.getContent();
                        //JSON RESPONSE AS STRING
                        String result = MyReservationActivity.convertStreamToString(instream);
                        JSONObject json = new JSONObject(result);
                        //RETURNED AS ARRAY OF DOCUMENTS TITLED DOCS
                        JSONArray array = json.getJSONArray("docs");
                        JSONObject jsonRes = (JSONObject) array.get(0);
                        String reservation = jsonRes.get("vehicleClass").toString();
                        instream.close();


                    if(reservation.equals(vehicleClass)){
                        scanResult = true;
                    }


                }catch(IOException e){
                    e.printStackTrace();
                }catch (JSONException e1){
                    e1.printStackTrace();
                }catch(Exception e2){
                    e2.printStackTrace();
                }

            }

        };
        Thread thread = new Thread(runnable);
        thread.start();


        try{
            thread.join();
        }catch(Exception e){
            return;
        }

        builder.setTitle("Scan result");
        if(scanResult)
        {
            builder.setMessage("Reservation Successully Changed!");
            Intent carActivityIntent = new Intent(QrScanner.this, CarActivity.class);
            QrScanner.this.startActivity(carActivityIntent);
        }
        else {
            builder.setMessage("Car not included in reservation class!");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        mScannerView.resumeCameraPreview(this);  //  use to Resume scanning
        mScannerView.stopCamera();
        //setContentView(R.layout.qr_code);
        //mScannerView.resumeCameraPreview(this);  //  use to Resume scanning

    }
}

