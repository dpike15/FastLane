package com.example.hertzfastlane;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by stevenjoy on 3/13/17.
 */
@RunWith(AndroidJUnit4.class)
public class MyReservationTest{

//    @Rule
//    public IntentsTestRule<LoginActivity> nLoginRule =
//            new IntentsTestRule<LoginActivity>(LoginActivity.class);

    @Rule
    public IntentsTestRule activityRule = new IntentsTestRule<>(
            UserActivity.class, true,    // initialTouchMode
            false);  // launchActivity. False to set intent.

    @Test
    public void testReservation() throws Exception {

        onView(withId(R.id.et_Username)).perform(typeText("dpike15")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.et_Password)).perform(typeText("test")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.b_Login)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), UserActivity.class)));

        Reservation reserve = new Reservation();
        //reserve.setMember_id();


        onView(withId(R.id.bMyReservation)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), MyReservationActivity.class)));
    }

//    @Test
//    public void testQR_Scanner() {
//
////        onView(withId(R.id.et_Username)).perform(typeText("dpike15")).perform(ViewActions.closeSoftKeyboard());
////        onView(withId(R.id.et_Password)).perform(typeText("test")).perform(ViewActions.closeSoftKeyboard());
////        onView(withId(R.id.b_Login)).perform(click());
//
//        Member member = new Member();
//        member.setLoyaltyPoints(275);
//        member.setFirst_NM("derek");
//        Log.d("name",member.getFirst_NM());
//
//        Intent user = new Intent();
//        user.setAction(Intent.ACTION_SEND);
//        user.setType("text/plain");
//
//        user.putExtra("member", member);
//        activityRule.launchActivity(user);
//
//        onView(withId(R.id.bMyReservation)).perform(click());
//
//       // onView(withId(R.id.bSelectVehicle)).perform(click());
//       // intended(hasComponent(new ComponentName(getTargetContext(), QrScanner.class)));
//
//    }

//    @Test
//    public void testRestart() {
//        Looper.prepare();
//        MyReservationActivity activity = new MyReservationActivity();
//        activity.finish();
//        setActivity(null);
//        getActivity();
//        getInstrumentation().callActivityOnRestart(activity);
//
//
//        onView(withId(R.id.progress_loader)).check(matches(not(isDisplayed())));
//    }

//    @Test // method to test if helpActivity loads from actionBar
//    public void testActionBar() throws Exception {
//        // logs in loads UserActivity class
//        onView(withId(R.id.et_Username)).perform(typeText("dpike15")).perform(ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.et_Password)).perform(typeText("test")).perform(ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.b_Login)).perform(click());
//
//        onView(withId(R.id.bMyReservation)).perform(click());
//
//        try { // activates menu actions bar
//            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
//            onView(withId(R.id.action_help)).perform(click());
//        } catch (Exception e) {
//        }  // clicks on need help, loads intented help activity
//
//        //intended(hasComponent(new ComponentName(getTargetContext(), HelpActivity.class)));
//        onView(withText("Need Help?")).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void test() throws Exception {
//        onView(withId(R.id.et_Username)).perform(typeText("dpike15")).perform(ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.et_Password)).perform(typeText("test")).perform(ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.b_Login)).perform(click());
//
//        onView(withId(R.id.bMyReservation)).perform(click());
//
//        try { // activates menu actions bar
//            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
//            onView(withText("Parking Lot Maps")).perform(click());
//        } catch (Exception e) {
//        }  // clicks on need help, loads intented help activity
//
//        //intended(hasComponent(new ComponentName(getTargetContext(), MapActivity.class)));
//
//        // Type text and then press the button.
//        onView(withId(R.id.imageView6)).check(matches(isDisplayed()));
//
//    }

}
