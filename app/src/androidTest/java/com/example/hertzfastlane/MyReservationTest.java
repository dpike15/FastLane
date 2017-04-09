package com.example.hertzfastlane;

import android.content.ComponentName;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by stevenjoy on 3/13/17.
 */

public class MyReservationTest{

    @Rule
    public IntentsTestRule<LoginActivity> nLoginRule =
            new IntentsTestRule<LoginActivity>(LoginActivity.class);





    @Test
    public void testReservation() throws Exception {

        onView(withId(R.id.et_Username)).perform(typeText("dpike15")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.et_Password)).perform(typeText("test")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.b_Login)).perform(click());
        //intended(hasComponent(new ComponentName(getTargetContext(), UserActivity.class)));

        onView(withId(R.id.bMyReservation)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), MyReservationActivity.class)));
    }

    @Test
    public void testQR_Scanner() {

        onView(withId(R.id.et_Username)).perform(typeText("dpike15")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.et_Password)).perform(typeText("test")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.b_Login)).perform(click());

        onView(withId(R.id.bMyReservation)).perform(click());

        onView(withId(R.id.bSelectVehicle)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), QrScanner.class)));

    }

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
