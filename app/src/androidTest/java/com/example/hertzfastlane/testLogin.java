package com.example.hertzfastlane;

import org.junit.Test;
import org.junit.Rule;

import android.content.ComponentName;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

/**
 * Created by stevenjoy on 2/19/17.
 */

public class testLogin {

    @Rule
    public IntentsTestRule<LoginActivity> nLoginRule =
            new IntentsTestRule<LoginActivity>(LoginActivity.class);
    @Test
    public void testLoginButton() throws Exception {

        onView(withId(R.id.et_Username)).perform(typeText("dpike15")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.et_Password)).perform(typeText("test")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.b_Login)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), UserActivity.class)));
    }
}








