package com.example.hertzfastlane;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.estimote.sdk.Nearable;
import com.example.hertzfastlane.estimote.NearableID;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.widget.GridLayout.HORIZONTAL;
import static com.estimote.sdk.Nearable.FirmwareState.APP;
import static com.estimote.sdk.cloud.model.BroadcastingPower.LEVEL_1;


/**
 * Created by stevenjoy on 3/9/17.
 */
@RunWith(AndroidJUnit4.class)
public class BeaconsTest {

    @Rule
    public ActivityTestRule<Beacons> nBeaconsActivityTestRule=
            new ActivityTestRule<Beacons>(Beacons.class);


    private String beaconIdentifier = "";

    @Test
    public void testBeacons() {

        //[Nearable{identifier='624ec2233b5f0546', hardwareVersion='SB0', firmwareVersion='Unknown', bootloaderVersion='SB1.0.0', firmwareState=APP, temperature=23.813, rssi=-97, isMoving=false, xAcceleration=500.0, yAcceleration=-109.375, zAcceleration=-1328.125, orientation=HORIZONTAL, currentMotionStateDuration=0, lastMotionStateDuration=13, batteryLevel=UNKNOWN, power=LEVEL_1, region=Region{identifier=nearable-624ec2233b5f0546, proximityUUID=d0d3fa86-ca76-45ec-9bd9-6af4624ec223, major=15199, minor=1350, secure=false}, type=Fridge, color=Mint Cocktail}];

        String nearables = "624ec2233b5f0546";

        //NearableID near = nearestBeacon.identifier;


       // beaconIdentifier = nearestBeacon.identifier;

        Beacons beacon1 = new Beacons();
        beacon1.beaconsInRange(nearables);



    }
}


