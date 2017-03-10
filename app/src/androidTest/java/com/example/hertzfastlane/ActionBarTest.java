package com.example.hertzfastlane;

/**
 * Created by stevenjoy on 3/8/17.
 */

import android.content.ComponentName;
import android.support.test.InstrumentationRegistry;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anyOf;

@RunWith(AndroidJUnit4.class)
public class ActionBarTest {

    @Rule
    public IntentsTestRule<LoginActivity> nActionBarRule =
            new IntentsTestRule<LoginActivity>(LoginActivity.class);


    @Test
    public void testActionBar() throws Exception {
        // logs in loads UserActivity class
        onView(withId(R.id.et_Username)).perform(typeText("dpike15")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.et_Password)).perform(typeText("test")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.b_Login)).perform(click());

        try { // activates menu actions bar
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        } catch (Exception e) {
        }  // clicks on need help, loads intented help activity
        onView(anyOf(withText("Need Help?"), withId(R.id.action_help))).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), HelpActivity.class)));
    }
}
