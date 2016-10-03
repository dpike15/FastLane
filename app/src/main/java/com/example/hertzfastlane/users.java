package com.example.hertzfastlane;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by dapik on 9/30/2016.
 */


/*
 * Users class parses JSON data from table users in AWS database
 */
@DynamoDBTable(tableName = "users")
public class users {
    @DynamoDBHashKey(attributeName = "username")
    public String getUsersname() {
        return usersname;
    }

    public void setUsersname(String usersname) {
        this.usersname = usersname;
    }

    @DynamoDBIndexRangeKey(attributeName = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String usersname;
    private String password;

    @DynamoDBAttribute(attributeName = "first_name")
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @DynamoDBAttribute(attributeName = "last_name")
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @DynamoDBAttribute(attributeName = "reservationVin")
    public String getReservationVin() {
        return reservationVin;
    }

    public void setReservationVin(String reservationVin) {
        this.reservationVin = reservationVin;
    }

    private String first_name;
    private String last_name;
    private String reservationVin;
}
