//package com.example.hertzfastlane;
//
//import junit.framework.TestCase;
//
//
//import org.json.JSONException;
//import org.junit.Test;
//
//import org.mockito.runners.MockitoJUnitRunner;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static junit.framework.Assert.assertEquals;
//import static junit.framework.Assert.assertFalse;
//import static junit.framework.Assert.assertTrue;
//
//public class ApiTest {
//
//    @Test(timeout=1000)
//    public void testLoginApi() throws IOException, JSONException {
//        assertTrue(APICalls.login("dpike15","test"));
//        Member member = APICalls.getUser();
//        assertEquals(member.getFirst_NM(),"Derek");
//    }
//
//    @Test(timeout = 1000)
//    public void testLoginApi2() throws IOException, JSONException {
//        assertFalse(APICalls.login("dpike15","da"));
//    }
//
//
//
//    @Test(expected = IOException.class,timeout = 1000)
//    public void testLoginApi3() throws IOException, JSONException {
//        assertFalse(APICalls.login("",""));
//    }
//
//
//    @Test(expected = JSONException.class,timeout = 1000)
//    public void testLoginApi4() throws IOException, JSONException {
//        assertFalse(APICalls.login("daps",""));
//    }
//
//
//    @Test(timeout = 500)
//    public void testCarData() throws JSONException, IOException {
//        String car_id = "93855";
//        Car carInfo = APICalls.getCarInfo(car_id);
//        assertEquals(car_id,carInfo.getCar_id());
//    }
//
//
//    //Testing looks for car that does not exist in database
//    @Test(expected=JSONException.class,timeout = 500)
//    public void testCarData2() throws JSONException, IOException {
//        String car_id = "1";
//        Car carInfo = APICalls.getCarInfo(car_id);
//    }
//
//    @Test(timeout = 500)
//    public void testWeatherApi() throws JSONException, IOException {
//        String lat = "24";
//        String lon = "-81";
//        String name = "Marathon";
//        Weather weather = APICalls.getWeather(lat,lon);
//        assertEquals(name,weather.getName());
//    }
//
//
//
//    @Test(expected = IOException.class,timeout = 500)
//    public void testWeatherApi2() throws JSONException, IOException {
//        String lat = "d";
//        String lon = "s";
//        Weather weather = APICalls.getWeather(lat,lon);
//
//    }
//
//    //Valid Reservation
//    @Test(timeout = 500)
//    public void testReservations() throws JSONException, IOException {
//        //Retrieved manually from database
//        Member member = new Member();
//        member.setMember_id("1");
//
//        Reservation reservation = APICalls.getReservation(member);
//        assertEquals("1",reservation.getMember_id());
//    }
//
//    //No Reservation Exists
//    @Test(expected = JSONException.class,timeout = 500)
//    public void testReservations2() throws JSONException, IOException {
//        //Retrieved manually from database
//        Member member = new Member();
//        member.setMember_id("0");
//
//        Reservation reservation = APICalls.getReservation(member);
//
//    }
//
//    @Test(expected = IOException.class,timeout = 500)
//    public void testReservations3() throws JSONException, IOException {
//        //Retrieved manually from database
//        Member member = new Member();
//        member.setMember_id("");
//
//        Reservation reservation = APICalls.getReservation(member);
//    }
//
//    //Test whether car can leave based on status inside AWS
//    //For testing car is set to "active" - able to leave
//    @Test(timeout = 500)
//    public void testExitConditions() throws JSONException, IOException {
//        List<String> carIds = new ArrayList<String>();
//        carIds.add("123");
//        carIds.add("1234");
//
//        String result = APICalls.checkExitConditions("1",carIds);
//        assertEquals("Success!",result);
//    }
//
//    //Fail Test
//    @Test(timeout = 500)
//    public void testExitConditionsFail() throws JSONException, IOException {
//        List<String> carIds = new ArrayList<String>();
//        carIds.add("123");
//
//        String result = APICalls.checkExitConditions("1",carIds);
//        assertEquals("FAILED",result);
//    }
//
//    //Fail Test
//    @Test(expected = JSONException.class,timeout = 500)
//    public void testExitConditionsFailJSON() throws JSONException, IOException {
//        List<String> carIds = new ArrayList<String>();
//        carIds.add("123");
//
//        String result = APICalls.checkExitConditions("0",carIds);
//        assertEquals("FAILED",result);
//    }
//
//    //Check that the right car_id is matched with the correct car
//    //Data known from Database
//    @Test(timeout = 500)
//    public void testBeacons() throws JSONException, IOException {
//        assertEquals("1234",APICalls.getCar_ID("a8209e97ce7e3ed6"));
//    }
//
//    @Test(expected = JSONException.class,timeout = 500)
//    public void testBeaconsJSONError() throws JSONException, IOException {
//        APICalls.getCar_ID("testID");
//    }
//
//    //Testing wrong car_vin
//    //car_vin should be "1234" - returned from AWS
//    @Test(timeout = 500)
//    public void testBeaconsFail() throws JSONException, IOException {
//        assertFalse("41".equals(APICalls.getCar_ID("a8209e97ce7e3ed6")));
//    }
//
//    //Using info known about database information (White Box)
//    @Test(timeout = 500)
//    public void testFleetCityCarType()throws JSONException,IOException{
//        String city = "Tampa";
//        String carType = "luxury";
//
//        assertEquals(3,APICalls.findFleet(city,carType).length());
//    }
//
//    @Test(expected=IOException.class,timeout = 1700)
//    public void testFleetCityCarTypeFail()throws JSONException,IOException{
//        String city = "Nashville";
//        String carType = "";
//
//       APICalls.findFleet(city,carType).length();
//    }
//
//
//    @Test(timeout = 500)
//    public void testFleetAirportCode()throws JSONException,IOException{
//        //TAMPA INT'L AIRPORT
//        String airportCode = "TPA";
//
//        assertEquals(7,APICalls.findFleetAirportCode(airportCode).length());
//    }
//}

package com.example.hertzfastlane;

import junit.framework.TestCase;


import org.json.JSONException;
import org.junit.Test;

import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class ApiTest {

    @Test(timeout=1000)
    public void testLoginApi() throws IOException, JSONException {
        assertTrue(APICalls.login("dpike15","test"));
        Member member = APICalls.getUser();
        assertEquals(member.getFirst_NM(),"Derek");
    }

    @Test(timeout = 1000)
    public void testLoginApi2() throws IOException, JSONException {
        assertFalse(APICalls.login("dpike15","da"));
    }



    @Test(expected = IOException.class,timeout = 1000)
    public void testLoginApi3() throws IOException, JSONException {
        assertFalse(APICalls.login("",""));
    }


    @Test(expected = JSONException.class,timeout = 1000)
    public void testLoginApi4() throws IOException, JSONException {
        assertFalse(APICalls.login("daps",""));
    }


    @Test(timeout = 500)
    public void testCarData() throws JSONException, IOException {
        String car_id = "93855";
        Car carInfo = APICalls.getCarInfo(car_id);
        assertEquals(car_id,carInfo.getCar_id());
    }


    //Testing looks for car that does not exist in database
    @Test(expected=JSONException.class,timeout = 500)
    public void testCarData2() throws JSONException, IOException {
        String car_id = "1";
        Car carInfo = APICalls.getCarInfo(car_id);
    }

//    @Test(timeout = 500)
//    public void testWeatherApi() throws JSONException, IOException {
//        String lat = "24";
//        String lon = "-81";
//        String name = "Marathon";
//        Weather weather = APICalls.getWeather(lat,lon);
//        assertEquals(name,weather.getName());
//    }



//    @Test(expected = IOException.class,timeout = 500)
//    public void testWeatherApi2() throws JSONException, IOException {
//        String lat = "d";
//        String lon = "s";
//        Weather weather = APICalls.getWeather(lat,lon);
//
//    }

    //Valid Reservation
    @Test(timeout = 500)
    public void testReservations() throws JSONException, IOException {
        //Retrieved manually from database
        Member member = new Member();
        member.setMember_id("1");

        Reservation reservation = APICalls.getReservation(member);
        assertEquals("1",reservation.getMember_id());
    }

    //No Reservation Exists
    @Test(expected = JSONException.class,timeout = 500)
    public void testReservations2() throws JSONException, IOException {
        //Retrieved manually from database
        Member member = new Member();
        member.setMember_id("0");

        Reservation reservation = APICalls.getReservation(member);

    }

    @Test(expected = IOException.class,timeout = 500)
    public void testReservations3() throws JSONException, IOException {
        //Retrieved manually from database
        Member member = new Member();
        member.setMember_id("");

        Reservation reservation = APICalls.getReservation(member);
    }

    //Test whether car can leave based on status inside AWS
    //For testing car is set to "active" - able to leave
//    @Test(timeout = 500)
//    public void testExitConditions() throws JSONException, IOException {
//        List<String> carIds = new ArrayList<String>();
//        carIds.add("123");
//        carIds.add("1234");
//
//        String result = APICalls.checkExitConditions("1",carIds);
//        assertEquals("Success!",result);
//    }

//    //Fail Test
//    @Test(timeout = 500)
//    public void testExitConditionsFail() throws JSONException, IOException {
//        List<String> carIds = new ArrayList<String>();
//        carIds.add("123");
//
//        String result = APICalls.checkExitConditions("1",carIds);
//        assertEquals("FAILED",result);
//    }

    //Fail Test
//    @Test(expected = JSONException.class,timeout = 500)
//    public void testExitConditionsFailJSON() throws JSONException, IOException {
//        List<String> carIds = new ArrayList<String>();
//        carIds.add("123");
//
//        String result = APICalls.checkExitConditions("0",carIds);
//        assertEquals("FAILED",result);
//    }

    //Check that the right car_id is matched with the correct car
    //Data known from Database
    @Test(timeout = 500)
    public void testBeacons() throws JSONException, IOException {
        assertEquals("1234",APICalls.getCar_ID("a8209e97ce7e3ed6"));
    }

    @Test(expected = JSONException.class,timeout = 500)
    public void testBeaconsJSONError() throws JSONException, IOException {
        APICalls.getCar_ID("testID");
    }

    //Testing wrong car_vin
    //car_vin should be "1234" - returned from AWS
    @Test(timeout = 500)
    public void testBeaconsFail() throws JSONException, IOException {
        assertFalse("41".equals(APICalls.getCar_ID("a8209e97ce7e3ed6")));
    }

    //Using info known about database information (White Box)
//    @Test(timeout = 500)
//    public void testFleetCityCarType()throws JSONException,IOException{
//        String city = "Tampa";
//        String carType = "luxury";
//
//        assertEquals(3,APICalls.findFleet(city,carType).length());
//    }

//    @Test(expected=IOException.class,timeout = 1700)
//    public void testFleetCityCarTypeFail()throws JSONException,IOException{
//        String city = "Nashville";
//        String carType = "";
//
//       APICalls.findFleet(city,carType).length();
//    }


//    @Test(timeout = 500)
//    public void testFleetAirportCode()throws JSONException,IOException{
//        //TAMPA INT'L AIRPORT
//        String airportCode = "TPA";
//
//        assertEquals(7,APICalls.findFleetAirportCode(airportCode).length());
//    }
}