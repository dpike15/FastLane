package com.example.hertzfastlane;



/**
 * Created by dapik on 9/22/2016.
 *
 * Class Member that maps JSON data from table members
 */

public class Member {
    private String customer_id;
    private String _id;

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

    private String username;
    private String password;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_rev() {
        return _rev;
    }

    public void set_rev(String _rev) {
        this._rev = _rev;
    }

    public String getFirst_NM() {
        return first_NM;
    }

    public void setFirst_NM(String first_NM) {
        this.first_NM = first_NM;
    }

    public String getMiddle_NM() {
        return middle_NM;
    }

    public void setMiddle_NM(String middle_NM) {
        this.middle_NM = middle_NM;
    }

    public String getLast_NM() {
        return last_NM;
    }

    public void setLast_NM(String last_NM) {
        this.last_NM = last_NM;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSms_Text() {
        return sms_Text;
    }

    public void setSms_Text(String sms_Text) {
        this.sms_Text = sms_Text;
    }

    public String getName_On_Card() {
        return name_On_Card;
    }

    public void setName_On_Card(String name_On_Card) {
        this.name_On_Card = name_On_Card;
    }

    private String _rev;



    private String first_NM;
    private String middle_NM;
    private String last_NM;
    private String gender;
    private String email;
    private String sms_Text;
    private String name_On_Card;

}

