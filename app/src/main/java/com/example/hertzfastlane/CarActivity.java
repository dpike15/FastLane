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

        TextView carTitle = (TextView) findViewById(R.id.tvMakeModel);
        carTitle.setText(car.getInfo().getYear() + " " + car.getInfo().getMake() + " " + car.getInfo().getModel());

        TextView mpg = (TextView) findViewById(R.id.tvMPG);
        mpg.setText(car.getInfo().getMpgCity() + " MPG");

        TextView passengers = (TextView) findViewById(R.id.tvPassenger);
        passengers.setText("Passengers: " + car.getInfo().getPassengers());

        TextView features= (TextView) findViewById(R.id.tvSatRadio);
        features.setText(car.getFeatures().get(0));

        TextView rate = (TextView) findViewById(R.id.tvRate);
        rate.setText(car.getInfo().getRate() + " USD");

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
