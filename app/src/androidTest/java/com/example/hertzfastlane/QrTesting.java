package com.example.hertzfastlane;

import android.os.Handler;
import android.os.Looper;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;

/**
 * Created by stevenjoy on 3/13/17.
 */

public class QrTesting {

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Rule
    public IntentsTestRule<LoginActivity> nLoginRule =
            new IntentsTestRule<LoginActivity>(LoginActivity.class);


    @Test
    public void run() {
        onView(withId(R.id.et_Username)).perform(typeText("dpike15")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.et_Password)).perform(typeText("test")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.b_Login)).perform(click());


        mHandler.post(new Runnable() {
            @Override
            public void run() {
                String nearables = "624ec2233b5f0546";
                Beacons beacon1 = new Beacons();
                beacon1.beaconsInRange(nearables);

                Log.d("TestingTag", "mCars = " + beacon1.nearableMap.containsValue(nearables));
                assertTrue(beacon1.nearableMap.containsValue(nearables));
            }
        });
    }

}
