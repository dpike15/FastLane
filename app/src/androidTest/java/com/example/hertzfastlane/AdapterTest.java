package com.example.hertzfastlane;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;

/**
 * Created by stevenjoy on 4/12/17.
 */

@RunWith(AndroidJUnit4.class)
public class AdapterTest {

    private TestingCarAdapter adapter;
    private Context mContext;


    @Rule
    public ActivityTestRule<Beacons> activityRule =
            new ActivityTestRule<>(Beacons.class, true, false);


    @Test
    public void testAdapterClass() {

        final List<Car> cars = new ArrayList<>();
        Car testCar = new Car();
        Info info = new Info();

        Member member = new Member();
        member.setLoyaltyPoints(275);
        member.setFirst_NM("derek");

        info.setYear("2017");
        info.setMake("Tesla");
        info.setModel("Model 3");
        info.setRate("199.99");
        testCar.setInfo(info);

        testCar.getInfo().getYear();
        testCar.getInfo().getMake();
        testCar.getInfo().getModel();
        testCar.getInfo().getRate();

        cars.add(testCar);

        Intent user = new Intent();

        user.setAction(Intent.ACTION_SEND);
        user.setType("text/plain");

        user.putExtra("member", member);

        activityRule.launchActivity(user);

        adapter = new TestingCarAdapter(cars);

        assertTrue(adapter.getmCars()==cars);
        assertTrue(adapter.getContext()==mContext);
        adapter.notifyDataSetChanged();
        onView(withId(R.id.rvTestingCar)).perform(RecyclerViewActions.scrollToPosition(3));

    }
}
