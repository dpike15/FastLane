package com.example.hertzfastlane;

/**
 * Created by dapik on 10/5/2016.
 */

public class Reservation {
    private String _id;
    private String _rev;
    private String reservation_Num;
    private String number_Of_Rental_Days;
    private String pick_Up;

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

    public String getReservation_Num() {
        return reservation_Num;
    }

    public void setReservation_Num(String reservation_Num) {
        this.reservation_Num = reservation_Num;
    }

    public String getNumber_Of_Rental_Days() {
        return number_Of_Rental_Days;
    }

    public void setNumber_Of_Rental_Days(String number_Of_Rental_Days) {
        this.number_Of_Rental_Days = number_Of_Rental_Days;
    }

    public String getPick_Up() {
        return pick_Up;
    }

    public void setPick_Up(String pick_Up) {
        this.pick_Up = pick_Up;
    }

    public String getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(String customer_Id) {
        this.customer_Id = customer_Id;
    }

    public String getCar_Vin() {
        return car_Vin;
    }

    public void setCar_Vin(String car_Vin) {
        this.car_Vin = car_Vin;
    }

    public String getReturnLocation() {
        return returnLocation;
    }

    public void setReturnLocation(String returnLocation) {
        this.returnLocation = returnLocation;
    }

    public String getPick_Up_Date() {
        return pick_Up_Date;
    }

    public void setPick_Up_Date(String pick_Up_Date) {
        this.pick_Up_Date = pick_Up_Date;
    }

    private String customer_Id;
    private String car_Vin;
    private String returnLocation;
    private String pick_Up_Date;






   ;
}
