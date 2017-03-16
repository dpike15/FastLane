package com.example.hertzfastlane;

//import android.support.test.espresso.intent.rule.IntentsTestRule;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.api.Api;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;

/**
 * Created by dapik on 3/15/2017.
 */
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import android.content.SharedPreferences;

@RunWith(MockitoJUnitRunner.class)
public class ApiTest {

//    @Mock
//    Context mMockContext;

    @Test
    public void testLoginApi() throws IOException, JSONException {

//        APICalls result = new APICalls();
//        String result2 = String.valueOf(APICalls.login("dpike15","test"));

        assertTrue(APICalls.login("dpike15","test"));

    }

    @Test
    public void testLoginApi2() throws IOException, JSONException {
        assertFalse(APICalls.login("dpike15","da"));
    }

    @Test(expected = IOException.class)
    public void testLoginApi3() throws IOException, JSONException {
        assertFalse(APICalls.login("",""));
    }


    @Test(expected = JSONException.class)
    public void testLoginApi4() throws IOException, JSONException {
        assertFalse(APICalls.login("daps",""));
    }


    @Test
    public void testCarData() throws JSONException, IOException {
        String car_id = "93855";
        Car carInfo = APICalls.getCarInfo(car_id);
        assertEquals(car_id,carInfo.getCar_id());
    }


    //Testing looks for car that does not exist in database
    @Test(expected=JSONException.class)
    public void testCarData2() throws JSONException, IOException {
        String car_id = "1";
        Car carInfo = APICalls.getCarInfo(car_id);
    }

    @Test
    public void testWeatherApi() throws JSONException, IOException {
        String lat = "24";
        String lon = "-81";
        String name = "Marathon";
        Weather weather = APICalls.getWeather(lat,lon);
        assertEquals(name,weather.getName());
    }



    @Test(expected = IOException.class)
    public void testWeatherApi2() throws JSONException, IOException {
        String lat = "d";
        String lon = "s";
        Weather weather = APICalls.getWeather(lat,lon);

    }

    //Valid Reservation
    @Test
    public void testReservations() throws JSONException, IOException {
        //Retrieved manually from database
        Member member = new Member();
        member.setMember_id("1");

        Reservation reservation = APICalls.getReservation(member);
        assertEquals("1",reservation.getMember_id());
    }

    //No Reservation Exists
    @Test(expected = JSONException.class)
    public void testReservations2() throws JSONException, IOException {
        //Retrieved manually from database
        Member member = new Member();
        member.setMember_id("0");

        Reservation reservation = APICalls.getReservation(member);

    }

    @Test(expected = IOException.class)
    public void testReservations3() throws JSONException, IOException {
        //Retrieved manually from database
        Member member = new Member();
        member.setMember_id("");

        Reservation reservation = APICalls.getReservation(member);
    }

    //Test whether car can leave based on status inside AWS
    //For testing car is set to "active" - able to leave
    @Test
    public void testExitConditions() throws JSONException, IOException {
        List<String> carIds = new ArrayList<String>();
        carIds.add("123");
        carIds.add("1234");

        String result = APICalls.checkExitConditions("1",carIds);
        assertEquals("Success!",result);
    }

    //Fail Test
    @Test
    public void testExitConditionsFail() throws JSONException, IOException {
        List<String> carIds = new ArrayList<String>();
        carIds.add("123");

        String result = APICalls.checkExitConditions("1",carIds);
        assertEquals("FAILED",result);
    }

    //Fail Test
    @Test(expected = JSONException.class)
    public void testExitConditionsFailJSON() throws JSONException, IOException {
        List<String> carIds = new ArrayList<String>();
        carIds.add("123");

        String result = APICalls.checkExitConditions("0",carIds);
        assertEquals("FAILED",result);
    }

    //Check that the right car_id is matched with the correct car
    //Data known from Database
    @Test
    public void testBeacons() throws JSONException, IOException {
        assertEquals("1234",APICalls.getCar_ID("a8209e97ce7e3ed6"));
    }

    @Test(expected = JSONException.class)
    public void testBeaconsJSONError() throws JSONException, IOException {
        APICalls.getCar_ID("testID");
    }

    //Testing wrong car_vin
    //car_vin should be "1234" - returned from AWS
    @Test
    public void testBeaconsFail() throws JSONException, IOException {
        assertFalse("41".equals(APICalls.getCar_ID("a8209e97ce7e3ed6")));
    }

    @Test
    public void testFleetByCity() throws JSONException,IOException {

    }
}
