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
import static junit.framework.Assert.fail;
import static org.hamcrest.core.IsNot.not;


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


//    public void setBeaconBackground() {
//        if(!mCars.isEmpty()){
//            findViewById(R.id.imageViewBeacons).setVisibility(View.GONE);
//            findViewById(R.id.tvLookingForBeacons).setVisibility(View.GONE);
//        } else {
//            findViewById(R.id.imageViewBeacons).setVisibility(View.VISIBLE);
//            findViewById(R.id.tvLookingForBeacons).setVisibility(View.VISIBLE);
//        }
//    }


//    @Test
//    public void testBackgroundList() {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                Looper.prepare();
//
//                final Beacons beacons = new Beacons();
//                try {
//                    final String nearables = "624ec2233b5f0546";
//                    beacons.beaconsInRange(nearables);
//                    beacons.clearList();
//
//
//
//                    if (beacons.getmCars().isEmpty()) {
//                        beacons.setBeaconBackground();
//                     onView(withId(R.id.imageViewBeacons)).check(matches(isDisplayed()));
//                     onView(withId(R.id.tvLookingForBeacons)).check(matches(isDisplayed()));
//                }
////                    else if (!beacons.getmCars().isEmpty()) {
////                 onView(withId(R.id.progress_loader)).check(matches(not(isDisplayed())));
////                 onView(withId(R.id.tvLookingForBeacons)).check(matches(not(isDisplayed())));
////            }
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        };
//        Thread thread = new Thread(runnable);
//        thread.start();
//
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}







