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

    public TestingCar(String make, String model, String year, String rate,String car_id) {
        this.mMake = make;
        this.mModel = model;
        this.mYear = year;
        this.mRate = rate;
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

   public String getCar_id(){return car_id; }

}

