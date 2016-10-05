package com.example.hertzfastlane;

/**
 * Created by dapik on 10/5/2016.
 */

public class Reservation {
    private String Reservation_Num;

    public String getReservation_Num() {
        return Reservation_Num;
    }

    public void setReservation_Num(String reservation_Num) {
        Reservation_Num = reservation_Num;
    }

    public String getNumber_Of_Rental_Days() {
        return Number_Of_Rental_Days;
    }

    public void setNumber_Of_Rental_Days(String number_Of_Rental_Days) {
        Number_Of_Rental_Days = number_Of_Rental_Days;
    }

    public String getPick_Up() {
        return Pick_Up;
    }

    public void setPick_Up(String pick_Up) {
        Pick_Up = pick_Up;
    }

    public String getCustomer_Id() {
        return Customer_Id;
    }

    public void setCustomer_Id(String customer_Id) {
        Customer_Id = customer_Id;
    }

    public String getCar_Vin() {
        return Car_Vin;
    }

    public void setCar_Vin(String car_Vin) {
        Car_Vin = car_Vin;
    }

    private String Number_Of_Rental_Days;
    private String Pick_Up;
    private String Customer_Id;
    private String Car_Vin;
}
