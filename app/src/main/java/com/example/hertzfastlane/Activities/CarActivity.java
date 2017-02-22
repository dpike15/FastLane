package com.example.hertzfastlane.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.hertzfastlane.R;
import com.example.hertzfastlane.estimote.beacons;
import com.example.hertzfastlane.models.Car;



public class CarActivity extends AppCompatActivity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        playVideo();

        Car car = beacons.getCarData();
        // Car car1 = QrScanner.getCar();

        TextView carTitle = (TextView) findViewById(R.id.tvMakeModel);
        carTitle.setText(car.getInfo().getYear() + " " + car.getInfo().getMake() + " " + car.getInfo().getModel());

        TextView mpg = (TextView) findViewById(R.id.tvMPG);
        mpg.setText(car.getInfo().getMpgCity() + " MPG");

        TextView passengers = (TextView) findViewById(R.id.tvPassenger);
        passengers.setText("Passengers: " + car.getInfo().getPassengers());

        TextView features= (TextView) findViewById(R.id.tvSatRadio);
        features.setText(car.getFeatures().get(0));

        TextView summary = (TextView) findViewById(R.id.tvSummary);
        summary.setText("The Cadillac 2016 CTS Sedan turns every drive into a masterful experience with assured performance and ingenious technology.");

        TextView rate = (TextView) findViewById(R.id.tvRate);
        rate.setText(car.getInfo().getRate() + " USD");

        Button chooseButton = (Button) findViewById(R.id.bChooseVehicle);
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                AlertDialog alertDialog;
                builder.setTitle("Updated: ");
                builder.setMessage("Successfully updated reservation! Please proceed to the gate.");
                alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Log.d("Message", "OnBackPressed");
        super.onBackPressed();
        Intent userActivityIntent = new Intent(CarActivity.this, UserActivity.class);
        CarActivity.this.startActivity(userActivityIntent);

    }

    public void playVideo() {
        VideoView myVideoView = (VideoView) findViewById(R.id.videoView);
        myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cts));
        myVideoView.start();
        myVideoView.seekTo(5);
    }

}