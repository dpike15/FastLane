package com.example.hertzfastlane;

/**
 * Created by dapik on 9/22/2016.
 * Car Object that maps JSON data from table Car in AWS
 */
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBDocument;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;

/**
 * Created by dapik on 9/20/2016.
 */
@DynamoDBTable(tableName = "Cars")
public class Car{
    private String vin;

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



    @DynamoDBAttribute(attributeName = "features")
    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    private List<String> features;

    @DynamoDBAttribute(attributeName = "info")
    public CarInfo getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(CarInfo carInfo) {
        this.carInfo = carInfo;
    }

    private CarInfo carInfo;

    //Hash Key used for searching
    @DynamoDBHashKey(attributeName = "VIN")
    public String getVin(){
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }


}