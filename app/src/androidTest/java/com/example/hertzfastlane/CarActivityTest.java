package com.example.hertzfastlane;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Rudy Trevino and stevenjoy on 4/10/17.
 */

@RunWith(JUnit4.class)
public class CarActivityTest {

    @Rule
    public IntentsTestRule activityRule = new IntentsTestRule<>(
            CarActivity.class, true,    // initialTouchMode
            false);  // launchActivity. False to set intent.

    @Test
    public void carOnCreateTest() {
// Type text and then press the button.
        final List<String> features = new ArrayList<>();
        features.add("satelite radio");

       //features.add("satelite radio");

        Car car = new Car();
        Info info = new Info();
        info.setMake("BMW");
        info.setModel("X5");
        info.setYear("2017");
        info.setMpgCity("18");
        info.setMpgHighway("24");
        info.setPassengers("5");
        info.setRate("79.99");
        car.setFeatures(features);
        car.setVehicleClass("Prestige");
        car.setInfo(info);
        car.setSummary("This is a test car object.");
        car.getInfo().getMake();
        car.getInfo().getModel();
        car.getInfo().getYear();
        car.getInfo().getMpgCity();
        car.getInfo().getPassengers();
        car.getInfo().getRate();
        car.setVideoURL("https://www.youtube.com/watch?v=M30XXrrK3lY");


        assertTrue(car.getInfo().getMake()=="BMW");
        assertTrue(car.getInfo().getModel()=="X5");
        assertTrue(car.getInfo().getYear()=="2017");
        assertTrue(car.getInfo().getMpgCity()=="18");
        assertTrue(car.getInfo().getPassengers()=="5");
        assertTrue(car.getInfo().getRate()=="79.99");

        Intent userActivityIntent = new Intent();
        userActivityIntent.setAction(Intent.ACTION_SEND);
        userActivityIntent.setType("text/plain");
        userActivityIntent.putExtra("car", car);
        activityRule.launchActivity(userActivityIntent);



        onView(withId(R.id.imageView8)).check(matches(isDisplayed()));

    }
}