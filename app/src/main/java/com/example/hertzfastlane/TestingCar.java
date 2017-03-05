package com.example.hertzfastlane;

/**
 * Created by rodolfotrevino on 3/2/17.
 */

public class TestingCar {
    private String mMake;
    private String mModel;
    private String mYear;
    private String mRate;

    public TestingCar(String make, String model, String year, String rate) {
        this.mMake = make;
        this.mModel = model;
        this.mYear = year;
        this.mRate = rate;
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

}

