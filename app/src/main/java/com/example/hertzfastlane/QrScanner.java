package com.example.hertzfastlane;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**

 /**
 * Created by Steven J on 9/22/2016.
 */
public class QrScanner extends Activity implements ZXingScannerView.ResultHandler {
    private String qrString;
    private String resultString;
    private ZXingScannerView mScannerView;
    //DynamoDB Mapper objects - JSON data
    private DynamoDBMapper mapper;
    private DynamoDBMapper mapperMembers;
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

        //Credentials for identity pools for Table Cars and members - AWS
        // Initialize the Amazon Cognito credentials provider for members Table
        CognitoCachingCredentialsProvider credentialsProviderMembers = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "us-east-1:d203cc02-4b6f-475d-84b1-93687e673058", // Identity Pool ID
                Regions.US_EAST_1 // Region
        );
        AmazonDynamoDBClient ddbClientMembers = new AmazonDynamoDBClient(credentialsProviderMembers);
        mapperMembers = new DynamoDBMapper(ddbClientMembers);


        // Initialize the Amazon Cognito credentials provider for Cars table
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "us-east-1:d471f9f6-bda2-4a1f-85c5-4cb99127c6d1", // Identity Pool ID
                Regions.US_EAST_1 // Region
        );
        //DB client and JSON mapper-Cars Table
        AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);
        mapper = new DynamoDBMapper(ddbClient);

        Runnable runnable = new Runnable(){

            @Override
            public void run(){
                car = mapper.load(Car.class,qrString);
                if(car != null){
                    member = mapperMembers.load(Member.class, car.getReservationId());
                    if(member == null){
                        resultString = "Member does not exist";
                    }else{
                        if(car.getStatus().equals("true")){
                            resultString = car.getVin() + " " + car.getCarInfo().getMake() + " " +
                                    car.getCarInfo().getModel() + " is currently already checked out!";
                        }else if(!car.getStatus().equals("true") &&
                            car.getVin().equals(member.getReservationVin())){
                            resultString = member.getFirst_name() + " " + member.getLast_name() +
                                    " checked in successfully with an " + car.getVin() + " " +
                                    car.getCarInfo().getMake() + " " +
                                    car.getCarInfo().getModel();
                            car.setStatus("true");
                            mapper.save(car);
                        }else{
                            resultString = "This car does not match your reservation!";
                        }
                    }
                }else{
                    resultString = "Car does not exist";
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

        try{
            thread.join();
        }catch(Exception e){
            return;
        }

        //builder.setTitle("Scan result");
        if(resultString != null)
        {
            //builder.setMessage(resultString);
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

