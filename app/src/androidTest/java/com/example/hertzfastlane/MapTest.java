package com.example.hertzfastlane;

/**
 * Created by stevenjoy on 2/18/17.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.Espresso.onView;


@RunWith(AndroidJUnit4.class)
public class MapTest {
    @Rule
    public ActivityTestRule<MapActivity> nMapActivityTestRule=
            new ActivityTestRule<MapActivity>(MapActivity.class);

    @Test
    public void test() throws Exception {
        // Type text and then press the button.
        onView(withId(R.id.imageView6)).check(matches(isDisplayed()));

    }
}