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
