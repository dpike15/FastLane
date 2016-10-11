package com.example.hertzfastlane;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

public class MapActivity extends AppCompatActivity {

    ImageView imageView;
    PhotoViewAttacher mAttacher;

    @Override  // Miami Map
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);
        imageView = (ImageView) findViewById(R.id.imageView6);
        Drawable bitmap = ContextCompat.getDrawable(this, R.drawable.parking_lot_map);
        imageView.setImageDrawable(bitmap);

        mAttacher = new PhotoViewAttacher(imageView);


    }
}
