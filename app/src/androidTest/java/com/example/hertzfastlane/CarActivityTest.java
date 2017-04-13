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
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;

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
        final List<String> features = new ArrayList<>();
        features.add("satelite radio");

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
        onView(withId(R.id.bChooseVehicle)).perform(scrollTo()).perform(click());
      //  onView(withText(R.string.ReserveToast)).check(matches(isDisplayed()));

//        onView(withId(R.id.bChooseVehicle)).perform(scrollTo()).perform(click())
//                .inRoot(isDialog())
//                .check(matches(withText(R.string.ReserveToast)))
//                .check(matches(isDisplayed()));

        onView(withId(R.id.bChooseVehicle)).check(matches(allOf(withText(R.string.ReserveToast), isDisplayed())));

//        onView(withText("Updated: ")).inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView())))
//                .check(matches(isDisplayed()));
      //  onView(withText("Updated: ")).inRoot(isDialog()).check(matches(isDisplayed()));

    }
    @Test
    public void test_Setters() {
        Car car = new Car();

        car.set_rev("hello");
        car.setAirport("RSW");
        car.setAirportCode("911");
        car.setCity("Naples");
        car.setState("Fl");
        car.setStatus("Y");
        car.setImage("5");
        car.setVin("009");

        assertTrue((car.get_rev()=="hello"));
        assertTrue(car.getAirport()=="RSW");
        assertTrue(car.getAirportCode()=="911");
        assertTrue(car.getCity()=="Naples");
        assertTrue(car.getState()=="Fl");
        assertTrue(car.getImage()=="5");
        assertTrue(car.getVin()=="009");
        assertTrue(car.getStatus()=="Y");
    }
}