package com.example.hertzfastlane.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.hertzfastlane.R;

import uk.co.senab.photoview.PhotoViewAttacher;

public class MapActivity extends AppCompatActivity {

    //
    private ImageView imageView;
    private PhotoViewAttacher mAttacher;

    @Override  // Miami Map
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        imageView = (ImageView) findViewById(R.id.imageView6);
        Drawable bitmap = ContextCompat.getDrawable(this, R.drawable.lot_map_2);
        imageView.setImageDrawable(bitmap);

        mAttacher = new PhotoViewAttacher(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_maps:
                startActivity(new Intent(this, MapActivity.class));
                return true;
            case R.id.action_help:
                startActivity(new Intent(this, HelpActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
