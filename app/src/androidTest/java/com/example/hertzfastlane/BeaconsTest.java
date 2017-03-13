package com.example.hertzfastlane;


import android.os.Handler;
import android.os.Looper;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.estimote.sdk.Nearable;
import com.estimote.sdk.repackaged.okhttp_v2_2_0.com.squareup.okhttp.internal.spdy.FrameReader;
import com.estimote.sdk.repackaged.retrofit_v1_9_0.retrofit.RestAdapter;
import com.example.hertzfastlane.estimote.NearableID;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.widget.GridLayout.HORIZONTAL;
import static com.estimote.sdk.Nearable.FirmwareState.APP;
import static com.estimote.sdk.cloud.model.BroadcastingPower.LEVEL_1;
import static junit.framework.Assert.assertTrue;


/**
 * Created by stevenjoy on 3/9/17.
 */
@RunWith(AndroidJUnit4.class)
public class BeaconsTest {

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Rule
    public ActivityTestRule<Beacons> nBeaconsActivityTestRule =
            new ActivityTestRule<Beacons>(Beacons.class);


    private String beaconIdentifier = "";

    @Test
    public void run() {
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



