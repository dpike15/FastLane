package com.example.hertzfastlane;

/**
 * Created by dapik on 9/22/2016.
 * Car Object that maps JSON data from table Car in AWS
 */
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by dapik on 9/20/2016.
 */
@DynamoDBTable(tableName = "Cars")
public class Car{
    private String vin;
    private String make;
    private String status;

    @DynamoDBAttribute(attributeName = "reservationId")
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    private int reservationId;

    @DynamoDBAttribute(attributeName = "status")
    public String getStatus(){ return status; }

    public void setStatus(String status) {
        this.status = status;
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

    private String model;
    private String color;
    private String miles;

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

    @DynamoDBAttribute(attributeName = "features")
    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
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

    private String mpgCity;
    private String mpgHighway;
    private String passengers;
    private String luggage;
    private String features;
    private String rate;
    private String transmission;

    //Hash Key used for searching
    @DynamoDBHashKey(attributeName = "VIN")
    public String getVin(){
        return vin;
    }


    @DynamoDBIndexRangeKey(attributeName = "make")
    public String getMake(){
        return make;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setMake(String make){
        this.make = make;
    }
}