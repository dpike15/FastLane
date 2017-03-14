package com.example.hertzfastlane;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rodolfotrevino on 3/2/17.
 */

public class TestingCarAdapter extends RecyclerView.Adapter<TestingCarAdapter.ViewHolder> {
    // Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views



    public static List<Car> getmCars() {
        return mCars;
    }

    // Store a member variable for the contacts
    private static List<Car> mCars;


    // Store the context for easy access
    private static Context mContext;

    // Pass in the contact array into the constructor
    public TestingCarAdapter(List<Car> cars) {
        //mCars = cars;
        //mContext = this.getContext();
        mCars = cars;
    }

    // Easy access to the context object in the recyclerview
    private static Context getContext() {
        return mContext;
    }

    @Override
    public TestingCarAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //Context context = parent.getContext();
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.testing_car, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TestingCarAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.makeModelTextView.setText(mCars.get(position).getInfo().getYear() + " " +
                mCars.get(position).getInfo().getMake() + " " + mCars.get(position).getInfo().getModel());
        viewHolder.dailyRateTextView.setText(mCars.get(position).getInfo().getRate());

        //Image from S3 Bucket
        Picasso.with(mContext)
                .load(mCars.get(position).getImageURL())
                .config(Bitmap.Config.RGB_565)
                .error(R.drawable.a8)
                .fit()
                .centerInside()
                .into(viewHolder.tvImage);

        //OnClickListener
       viewHolder.tvImage.setOnTouchListener(new View.OnTouchListener(){

           @Override
           public boolean onTouch(View v, MotionEvent event) {
               ProgressDialog dialog = new ProgressDialog(v.getContext());
               dialog.setMessage("Working....");
               dialog.show();
               //Beacons.pos = position;
               Car car = mCars.get(position);
               //Launch CarActivity
               Intent userActivityIntent = new Intent(viewHolder.tvImage.getContext(),CarActivity.class);
               userActivityIntent.putExtra("car", car);
               viewHolder.tvImage.getContext().startActivity(userActivityIntent);

               return true;
           }

        });

    }

    @Override
    public int getItemCount() {
        return mCars.size();
    }


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView makeModelTextView;
        public TextView dailyRateTextView;
        public ImageView tvImage;
        public ImageView tvBackground;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            makeModelTextView = (TextView) itemView.findViewById(R.id.tvMakeModel);
            dailyRateTextView = (TextView) itemView.findViewById(R.id.tvDailyRate);
            tvImage = (ImageView) itemView.findViewById(R.id.tvCarImage);
            tvBackground = (ImageView) itemView.findViewById(R.id.rvTestingCar);

        }
    }


}
