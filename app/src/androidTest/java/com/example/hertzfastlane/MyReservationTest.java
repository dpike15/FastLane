package com.example.hertzfastlane;

import android.content.ComponentName;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.hertzfastlane.APICalls.getReservation;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.IsNot.not;


/**
 * Created by stevenjoy on 3/13/17.
 */
@RunWith(AndroidJUnit4.class)
public class MyReservationTest{

    @Rule
    public IntentsTestRule<LoginActivity> nLoginRule =
            new IntentsTestRule<LoginActivity>(LoginActivity.class);


    @Test
    public void testReservation() throws Exception {

        onView(withId(R.id.et_Username)).perform(typeText("dpike15")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.et_Password)).perform(typeText("test")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.b_Login)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), UserActivity.class)));

   //     Member member = new Member();
        Reservation reserve = new Reservation();
        reserve.setMember_id("12");
        reserve.setReservation_id("123");
        reserve.setCar_Vin("1234");
        reserve.setStatus("yes");
        reserve.setNumber_Of_Rental_Days("4");
        reserve.setReturnLocation("Naples");
        reserve.setPick_Up("hood");
        reserve.setPick_Up_Date("birthday");
        reserve.setVehicleClass("prestige");

//        assertNotNull(getReservation(member));
        assertTrue(reserve.getVehicleClass()=="prestige");
        assertTrue(reserve.getMember_id()=="12");
        assertTrue(reserve.getReservation_id()=="123");
        assertTrue(reserve.getCar_Vin()=="1234");
        assertTrue(reserve.getPick_Up()=="hood");
        assertTrue(reserve.getReturnLocation()=="Naples");
        assertTrue(reserve.getNumber_Of_Rental_Days()=="4");
        assertTrue(reserve.getStatus()=="yes");
        assertTrue(reserve.getPick_Up_Date()=="birthday");

        onView(withId(R.id.bMyReservation)).perform(click());
        onView(withId(R.id.bSelectVehicle)).perform(click());

        intended(hasComponent(new ComponentName(getTargetContext(), MyReservationActivity.class)));
    }
}
