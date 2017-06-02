package com.example.hertzfastlane;

import android.content.Intent;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import static junit.framework.Assert.assertTrue;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anyOf;

/**
 * Created by stevenjoy on 3/13/17.
 */

@RunWith(AndroidJUnit4.class)

public class LoyaltyPointsTest {

    @Rule
    public IntentsTestRule activityRule = new IntentsTestRule<>(
            UserActivity.class, true,    // initialTouchMode
            false);  // launchActivity. False to set intent.

    @Test
    public void testLoyalyPoints() throws Exception {

        Member member = new Member();
        member.setLoyaltyPoints(275);
        member.setFirst_NM("derek");
        Log.d("name",member.getFirst_NM());

        Intent user = new Intent();
        user.setAction(Intent.ACTION_SEND);
        user.setType("text/plain");

        user.putExtra("member", member);
        activityRule.launchActivity(user);

        assertTrue(member.getLoyaltyPoints()==275);
        onView(withId(R.id.tvPoints)).check(matches(isDisplayed()));
    }
}
