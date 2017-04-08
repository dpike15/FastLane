package com.example.hertzfastlane;

import java.util.ArrayList;

/**
 * Created by rodolfotrevino on 3/2/17.
 */

public class TestingCar {
    private String mMake;
    private String mModel;
    private String mYear;
    private String mRate;
    private String mBeaconName;
    private String car_id;
    private String imageURL;

    public TestingCar(String make, String model, String year, String rate, String car_id, String imageURL) {
        this.mMake = make;
        this.mModel = model;
        this.mYear = year;
        this.mRate = rate;
        this.imageURL = imageURL;
        //this.mBeaconName=name;
        this.car_id = car_id;
    }

    public String getMake() {
        return mMake;
    }

    public String getModel() {
        return mModel;
    }

    public String getYear() {
        return mYear;
    }

    public String getRate() {
        return mRate;
    }

    public String getName() {
        return mBeaconName;
    }

    public String getCar_id() {
        return car_id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}

