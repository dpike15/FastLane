package com.example.hertzfastlane;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static com.example.hertzfastlane.R.styleable.RecyclerView;

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
        public ImageView tvImage;



        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            makeModelTextView = (TextView) itemView.findViewById(R.id.tvMakeModel);
            dailyRateTextView = (TextView) itemView.findViewById(R.id.tvDailyRate);
            tvImage = (ImageView) itemView.findViewById(R.id.tvCarImage);
        }
    }

    // Store a member variable for the contacts
    private List<TestingCar> mCars;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public TestingCarAdapter(List<TestingCar> cars) {
        //mCars = cars;
        //mContext = context;
        mCars = cars;
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
        viewHolder.makeModelTextView.setText(mCars.get(position).getYear() + " " +
                mCars.get(position).getMake() + " " + mCars.get(position).getModel());
        viewHolder.dailyRateTextView.setText(mCars.get(position).getRate());

            Uri imgUri= Uri.parse("https://s3.amazonaws.com/testimagesateam/denali+copy.png");
            viewHolder.tvImage.setImageURI(imgUri);



    }

    @Override
    public int getItemCount() {
        return mCars.size();
    }

}
