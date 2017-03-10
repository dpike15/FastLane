package com.example.hertzfastlane;

/**
 * Created by dapik on 10/5/2016.
 */

public class Reservation {

    public String getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(String reservation_id) {
        this.reservation_id = reservation_id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
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

    private String reservation_id;
    private String number_Of_Rental_Days;
    private String pick_Up;
    private String status;

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    private String vehicleClass;
    private String member_id;
    private String car_Vin;
    private String returnLocation;
    private String pick_Up_Date;






   ;
}
