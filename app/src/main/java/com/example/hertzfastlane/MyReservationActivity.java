package com.example.hertzfastlane;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class MyReservationActivity extends AppCompatActivity {
    DynamoDBMapper carMapper;
    Car car;
    users member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservation);

        // Initialize the Amazon Cognito credentials provider for Cars table
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "us-east-1:d471f9f6-bda2-4a1f-85c5-4cb99127c6d1", // Identity Pool ID
                Regions.US_EAST_1 // Region
        );
        //DB client and JSON mapper-Cars Table
        AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);
        carMapper = new DynamoDBMapper(ddbClient);

        //Getting user information from login
        member = LoginActivity.getMember();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //Accessing Car based on reservationßß
                car = carMapper.load(Car.class,member.getReservationVin());

                //Updating text fields to load static information
                TextView carTitle = (TextView) findViewById(R.id.textView);
                carTitle.setText(car.getMake() +  " " + car.getModel());

                TextView carMpg = (TextView) findViewById(R.id.textView2);
                carMpg.setText("MPG: City: "  + car.getMpgCity() + " Hwy: " + car.getMpgHighway() );

                TextView passengers = (TextView) findViewById(R.id.textView3);
                passengers.setText("Passengers: " + car.getPassengers());

                TextView luggage = (TextView) findViewById(R.id.textView4);
                luggage.setText("Luggage: " + car.getLuggage());

                TextView trasnmission = (TextView) findViewById(R.id.textView5);
                trasnmission.setText("Transmission: " + car.getTransmission());

                TextView features = (TextView) findViewById(R.id.textView6);
                features.setText(car.getFeatures());

                TextView rate = (TextView) findViewById(R.id.textView7);
                rate.setText("Rate: " + car.getRate() + " Daily");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        try{
            thread.join();
        }catch(Exception e){
            return;
        }


    }


}
