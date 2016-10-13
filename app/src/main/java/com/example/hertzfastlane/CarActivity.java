package com.example.hertzfastlane;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;



public class CarActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        Car car = QrScanner.getCar();

        TextView carTitle = (TextView) findViewById(R.id.textView);
        carTitle.setText(car.getInfo().getMake() + " " + car.getInfo().getModel());

        TextView mpg = (TextView) findViewById(R.id.textView2);
        carTitle.setText("MPG " + car.getInfo().getMpgCity() + " CITY / " + car.getInfo().getMpgHighway() + " HWY");

        TextView passengers = (TextView) findViewById(R.id.textView3);
        carTitle.setText("Passengers: " + car.getInfo().getPassengers());

        TextView Luggage= (TextView) findViewById(R.id.textView4);
        carTitle.setText("Luggage " + car.getInfo().getLuggage());

        TextView transmission = (TextView) findViewById(R.id.textView5);
        carTitle.setText("Transmission: " + car.getInfo().getTransmission());

        TextView features= (TextView) findViewById(R.id.textView6);
        carTitle.setText(car.getFeatures().get(0));

        TextView rate = (TextView) findViewById(R.id.textView7);
        carTitle.setText("Daily Rate: " + car.getInfo().getRate());

    }

    @Override
    public void onBackPressed()
    {
        Log.d("Message", "OnBackPressed");
        super.onBackPressed();
        Intent userActivityIntent = new Intent(CarActivity.this, UserActivity.class);
        CarActivity.this.startActivity(userActivityIntent);

    }
}
