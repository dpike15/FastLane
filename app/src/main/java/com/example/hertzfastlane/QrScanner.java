package com.example.hertzfastlane;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.google.zxing.Result;

import java.net.MalformedURLException;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**

 /**
 * Created by Steven J on 9/22/2016.
 */
public class QrScanner extends Activity implements ZXingScannerView.ResultHandler {
    private String qrString;
    private String resultString;
    private ZXingScannerView mScannerView;
    //DynamoDB Mapper objects - JSON dat
    Car car;
    Member member;

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

        Runnable runnable = new Runnable(){

            @Override
            public void run(){
                CloudantClient client;
                try{
                    client = Connection.getClient();
                    Database carsdb = client.database("cars",false);

                    String selectorCars = "\"selector\": {\"vin\": \"" + qrString + "\"}";

                    List<Car> cars = carsdb.findByIndex(selectorCars,Car.class);

                    Car scannedCar = cars.get(0);


                    resultString = scannedCar.getInfo().getMake() + " " + scannedCar.getInfo()
                            .getModel() + " scanned.";

                }catch(MalformedURLException e){
                    e.printStackTrace();
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
        if(resultString != null)
        {
            builder.setMessage(resultString);
            Intent carActivityIntent = new Intent(QrScanner.this, CarActivity.class);
            QrScanner.this.startActivity(carActivityIntent);
        }
        else
            builder.setMessage("Something is fucked");
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();

        mScannerView.resumeCameraPreview(this);  //  use to Resume scanning
        mScannerView.stopCamera();
        //setContentView(R.layout.qr_code);
        //mScannerView.resumeCameraPreview(this);  //  use to Resume scanning

    }
}

