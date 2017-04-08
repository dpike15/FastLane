package com.example.hertzfastlane;


import android.os.Handler;
import android.os.Looper;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.estimote.sdk.Nearable;
import com.estimote.sdk.repackaged.okhttp_v2_2_0.com.squareup.okhttp.internal.spdy.FrameReader;
import com.estimote.sdk.repackaged.retrofit_v1_9_0.retrofit.RestAdapter;
import com.example.hertzfastlane.estimote.NearableID;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.widget.GridLayout.HORIZONTAL;
import static com.estimote.sdk.Nearable.FirmwareState.APP;
import static com.estimote.sdk.cloud.model.BroadcastingPower.LEVEL_1;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;


/**
 * Created by stevenjoy and rtreving on 3/9/17.
 */
@RunWith(AndroidJUnit4.class)
public class BeaconsTest {

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Rule
    public ActivityTestRule<Beacons> nBeaconsActivityTestRule =
            new ActivityTestRule<Beacons>(Beacons.class);

    @Test
    public void run() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                String nearables = "624ec2233b5f0546";
                Beacons beacon1 = new Beacons();
                beacon1.nearableMap.put("624ec2233b5f0546", "624ec2233b5f0546");

                // onView(withId(R.id.rvTestingCar)).perform(RecyclerViewActions.scrollToPosition(3));


                Log.d("TestingTag", "mCars = " + beacon1.nearableMap.containsValue(nearables));
                assertTrue(beacon1.nearableMap.containsValue(nearables));

            }
        });
    }

    @Test
    public void testClearList() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                final Beacons beacons = new Beacons();
                try {
                    final String nearables = "624ec2233b5f0546";
                    beacons.beaconsInRange(nearables);
                    beacons.clearList();

                    assertTrue(beacons.getmCars().isEmpty());
                    assertTrue(beacons.getNearableMap().isEmpty());
                    assertTrue(beacons.getCar_id() == null);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        };
        Thread thread = new Thread(runnable);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


//    @Test
//    public void testBeaconsInRange () {
//
//    }





