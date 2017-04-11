package com.example.hertzfastlane;

import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.ImageView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.hertzfastlane.EspressoTestsMatchers.withDrawable;

/**
 * Created by rodolfotrevino on 3/14/17.
 */

@RunWith(AndroidJUnit4.class)
public class ImageLoadingTest {
    @Rule
    public IntentsTestRule activityRule = new IntentsTestRule<>(
            UserActivity.class, true,    // initialTouchMode
            false);  // launchActivity. False to set intent.

    @Test
    public void test() throws Exception {

        Member member = new Member();
        member.setLoyaltyPoints(275);
        member.setFirst_NM("derek");
        Log.d("name",member.getFirst_NM());

        Intent user = new Intent();
        user.setAction(Intent.ACTION_SEND);
        user.setType("text/plain");

        user.putExtra("member", member);
        activityRule.launchActivity(user);

        onView(withId(R.id.home_bg)).check(matches(withDrawable(R.drawable.login_bg_dark_6)));
        //ViewInteraction check = onView(withId(R.id.home_bg)).check(matches(withId(R.drawable.login_bg_dark_6)));
    }
}
