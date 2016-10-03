package com.example.hertzfastlane;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBDocument;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by dapik on 10/3/2016.
 */
@DynamoDBDocument
public class CarInfo {
    private String mpgCity;
    private String mpgHighway;
    private String passengers;
    private String luggage;
    private String rate;
    private String transmission;
    private String make;
    private String model;
    private String color;
    private String miles;

    public void setMake(String make){
        this.make = make;
    }

    @DynamoDBAttribute(attributeName = "make")
    public String getMake(){
        return make;
    }

    @DynamoDBAttribute(attributeName = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @DynamoDBAttribute(attributeName = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @DynamoDBAttribute(attributeName = "miles")
    public String getMiles() {
        return miles;
    }

    public void setMiles(String miles) {
        this.miles = miles;
    }



    @DynamoDBAttribute(attributeName = "mpgCity")
    public String getMpgCity() {
        return mpgCity;
    }

    public void setMpgCity(String mpgCity) {
        this.mpgCity = mpgCity;
    }

    @DynamoDBAttribute(attributeName = "mpgHighway")
    public String getMpgHighway() {
        return mpgHighway;
    }

    public void setMpgHighway(String mpgHighway) {
        this.mpgHighway = mpgHighway;
    }

    @DynamoDBAttribute(attributeName = "passengers")
    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    @DynamoDBAttribute(attributeName = "luggage")
    public String getLuggage() {
        return luggage;
    }

    public void setLuggage(String luggage) {
        this.luggage = luggage;
    }

    @DynamoDBAttribute(attributeName = "rate")
    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @DynamoDBAttribute(attributeName = "transmission")
    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

}
