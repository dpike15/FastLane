package com.example.hertzfastlane;

/**
 * Created by stevenjoy on 3/8/17.
 */

import android.content.ComponentName;
import android.content.Intent;

import android.support.test.InstrumentationRegistry;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class ActionBarTest {

    @Rule
    public IntentsTestRule activityRule = new IntentsTestRule<>(
            UserActivity.class, true,    // initialTouchMode
            false);  // launchActivity. False to set intent.

    @Test // method to test if helpActivity loads from actionBar
    public void testActionBar() throws Exception {
    // logs in loads UserActivity class

        Member member = new Member();
        member.setLoyaltyPoints(275);
        member.setFirst_NM("derek");
        Log.d("name",member.getFirst_NM());

        Intent user = new Intent();
        user.setAction(Intent.ACTION_SEND);
        user.setType("text/plain");

        user.putExtra("member", member);
        activityRule.launchActivity(user);
        // Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, user);
        // intending(toPackage("com.example.hertzfastlane")).respondWith(result);

        try { // activates menu actions bar
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        } catch (Exception e) {
        }  // clicks on need help, loads intented help activity
        onView(withText(R.string.action_help)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), HelpActivity.class)));
    }
}
