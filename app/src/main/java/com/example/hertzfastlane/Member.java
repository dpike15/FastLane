package com.example.hertzfastlane;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by dapik on 9/22/2016.
 *
 * Class Member that maps JSON data from table members
 */

public class Member {
    private String customer_id;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSMS_Text() {
        return SMS_Text;
    }

    public void setSMS_Text(String SMS_Text) {
        this.SMS_Text = SMS_Text;
    }

    private String username;
    private String password;

    public String getFirst_NM() {
        return First_NM;
    }

    public void setFirst_NM(String first_NM) {
        First_NM = first_NM;
    }

    public String getMiddle_NM() {
        return Middle_NM;
    }

    public void setMiddle_NM(String middle_NM) {
        Middle_NM = middle_NM;
    }

    public String getLast_NM() {
        return Last_NM;
    }

    public void setLast_NM(String last_NM) {
        Last_NM = last_NM;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSMS_TEXT() {
        return SMS_Text;
    }

    public void setSMS_TEXT(String SMS_TEXT) {
        this.SMS_Text = SMS_TEXT;
    }

    public String getName_On_Card() {
        return Name_On_Card;
    }

    public void setName_On_Card(String name_On_Card) {
        Name_On_Card = name_On_Card;
    }

    private String First_NM;
    private String Middle_NM;
    private String Last_NM;
    private String Gender;
    private String Email;
    private String SMS_Text;
    private String Name_On_Card;

}

