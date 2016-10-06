package com.example.hertzfastlane;

import java.util.List;

/**
 * Created by dapik on 10/5/2016.
 */


/*
 * Deserializer class Car for database "Cars"
 */
public class Car {
    private String vin;
    private List<String> features;
    private String image;

    private String status;
    private Info info;

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
