package com.example.hertzfastlane;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rodolfotrevino on 3/2/17.
 */

public class TestingCarAdapter extends RecyclerView.Adapter<TestingCarAdapter.ViewHolder> {
    // Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView makeModelTextView;
        public TextView dailyRateTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            makeModelTextView = (TextView) itemView.findViewById(R.id.tvMakeModel);
            dailyRateTextView = (TextView) itemView.findViewById(R.id.tvDailyRate);

        }
    }

    // Store a member variable for the contacts
    private TestingCar[] cars;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public TestingCarAdapter(TestingCar[] cars) {
        //mCars = cars;
        //mContext = context;
       this.cars = cars;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
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
    public void onBindViewHolder(TestingCarAdapter.ViewHolder viewHolder, int position) {

        viewHolder.makeModelTextView.setText(cars[position].getYear() + " " +
                cars[position].getMake() + " " + cars[position].getModel());
        viewHolder.dailyRateTextView.setText(cars[position].getRate());
    }

    @Override
    public int getItemCount() {

        return cars.length;
    }

}
