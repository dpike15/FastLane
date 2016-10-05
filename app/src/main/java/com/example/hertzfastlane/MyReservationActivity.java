package com.example.hertzfastlane;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.s3.model.transform.Unmarshallers;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.Database;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


import static android.R.attr.onClick;

public class MyReservationActivity extends AppCompatActivity {
    DynamoDBMapper carMapper;
    Car car;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservation);

        //Getting user information from login
        member = LoginActivity.getMember();

            Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //Running CloudantDB instead of AWS
                CloudantClient client;
                try {

                    client = Connection.getClient();
                    //Accessing Cars Database
                    Database reservationsdb = client.database("reservations",false);

                    //Selecting Document using a JSON selector : VIN number
                    String selectorRes = "\"selector\": {\"Customer_Id\": \"" + member.getCustomer_id()+ "\"}";

                    List<Reservation> reservations = reservationsdb.findByIndex(selectorRes,Reservation.class);

                    Reservation currentRes = reservations.get(0);

                    Database carsdb = client.database("cars",false);

                    String selector = "\"selector\": {\"vin\": \"" + currentRes.getCar_Vin()+ "\"}";

                    List<Car> cars = carsdb.findByIndex(selector,Car.class);

                    Car car = cars.get(0);

                    List<String> features = car.getFeatures();

                    //Setting textViews with Dynamic Data
                    TextView carTitle = (TextView) findViewById(R.id.textView);
                    carTitle.setText(car.getInfo().getYear() + " " + car.getInfo().getMake() +  " " + car.getInfo().getModel());

                    TextView carMpg = (TextView) findViewById(R.id.textView2);
                    carMpg.setText("MPG: City: "  + car.getInfo().getMpgCity() + " Hwy: " + car.getInfo().getMpgHighway() );

                    TextView passengers = (TextView) findViewById(R.id.textView3);
                    passengers.setText("Passengers: " + car.getInfo().getPassengers());

                    TextView luggage = (TextView) findViewById(R.id.textView4);
                    luggage.setText("Luggage: " + car.getInfo().getLuggage());

                    TextView trasnmission = (TextView) findViewById(R.id.textView5);
                    trasnmission.setText("Transmission: " + car.getInfo().getTransmission());

                    TextView featuresText = (TextView) findViewById(R.id.textView6);
                    featuresText.setText(features.get(1));

                    TextView rate = (TextView) findViewById(R.id.textView7);
                    rate.setText("Rate: " + car.getInfo().getRate() + " Daily");
                } catch (MalformedURLException e){
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


        Button upgradeButton = (Button) findViewById(R.id.button);
       upgradeButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               sendSMS("8605978596","Testing message");
           }
       });

    }
    public static Drawable LoadImage(String url){
        try{
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is,"SrcName");

            return d;
        }catch(Exception e){
            return null;
        }
    }

    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

}
