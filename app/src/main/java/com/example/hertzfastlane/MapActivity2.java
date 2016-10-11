package com.example.hertzfastlane;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import android.graphics.drawable.Drawable;

import uk.co.senab.photoview.PhotoViewAttacher;


public class MapActivity2 extends AppCompatActivity {


    ImageView imageView;
    PhotoViewAttacher mAttacher;

    @Override // Tampa map
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map3);

        imageView = (ImageView) findViewById(R.id.imageView5);
        Drawable bitmap = ContextCompat.getDrawable(this, R.drawable.airport_orlando);
        imageView.setImageDrawable(bitmap);

        mAttacher = new PhotoViewAttacher(imageView);

    }
}




/*
 imageView = (ImageView) findViewById(R.id.imageView5);
        SGD = new ScaleGestureDetector(this, new ScaleListener());
        Drawable d = imageView.getDrawable();


        RectF drawableRect = new RectF(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        RectF viewRect = new RectF(0, 0, imageView.getWidth(), imageView.getHeight());
        matrix.setRectToRect(drawableRect, viewRect, Matrix.ScaleToFit.CENTER);
        imageView.setImageMatrix(matrix);

    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            Drawable d = imageView.getDrawable();

            float offsetX = (imageView.getWidth() - d.getIntrinsicWidth()) / 2F;
            float offsetY = (imageView.getHeight() - d.getIntrinsicHeight()) / 2F;

            float centerX = imageView.getWidth() / 2F;
            float centerY = imageView.getHeight() / 2F;


            scale = scale * detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 5f));

            matrix.setScale(scale, scale, centerX, centerY);
            matrix.preTranslate(offsetX, offsetY);

            imageView.setImageMatrix(matrix);
            return true;
        }


    }


    public boolean onTouchEvent(MotionEvent event) {
        SGD.onTouchEvent(event);
        return true;
    }
  */