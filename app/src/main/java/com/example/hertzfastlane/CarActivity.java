package com.example.hertzfastlane;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.net.URI;


public class CarActivity extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    final Context context = this;

    static Car car;
    String videoURL = "X6BwCS9ORGk";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        Intent intent = getIntent();
        car = (Car) intent.getSerializableExtra("car");

        TextView classTitle = (TextView) findViewById(R.id.tvCollection);
        classTitle.setText(car.getVehicleClass().toUpperCase());

//
        TextView carTitle = (TextView) findViewById(R.id.tvMakeModel);
        carTitle.setText(car.getInfo().getYear() + " " + car.getInfo().getMake() + " " + car.getInfo().getModel());

        TextView mpg = (TextView) findViewById(R.id.tvMPG);
        mpg.setText(car.getInfo().getMpgCity() + " MPG");

        TextView passengers = (TextView) findViewById(R.id.tvPassenger);
        passengers.setText("Passengers: " + car.getInfo().getPassengers());

        TextView features = (TextView) findViewById(R.id.tvSatRadio);
        features.setText(car.getFeatures().get(0));

        TextView summary = (TextView) findViewById(R.id.tvSummary);
        // summary.setText("The cadillac 2016 CTS Sedan turns every drive into a masterful experience with assured performance and ingenious technology.");
        summary.setText(car.getSummary());

        TextView rate = (TextView) findViewById(R.id.tvRate);
        rate.setText(car.getInfo().getRate() + " USD");

        VideoView videoView = ((VideoView) findViewById(R.id.videoView));

        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        MediaController mc = new MediaController(context);
        mc.setMediaPlayer((VideoView)findViewById(R.id.videoView));
        ((VideoView) findViewById(R.id.videoView)).setMediaController(mc);
        Uri clip = Uri.parse(car.getVideoURL());
        videoView.setVideoURI(clip);
        videoView.requestFocus();
        videoView.start();

/*
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.videoView);
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(car.getVideoURL());
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        playVideo();
**/
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

//    @Override
//    public void onBackPressed() {
//        Log.d("Message", "OnBackPressed");
//        super.onBackPressed();
//        Intent userActivityIntent = new Intent(CarActivity.this, Beacons.class);
//        CarActivity.this.startActivity(userActivityIntent);
//
//    }

//    public void playVideo() {
//        //youTubePlayerView.initialize(PlayerConfig.API_KEY, onInitializedListener);
//        youTubePlayerView.initialize("AIzaSyADBw6Hhc3WXE7GZ4U9CpA1Lyweneq15YI", onInitializedListener);
//
//    }

    public static Car getCar() {
        return car;

    }

}
