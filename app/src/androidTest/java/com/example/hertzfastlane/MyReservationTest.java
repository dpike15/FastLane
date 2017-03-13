package com.example.hertzfastlane;

import android.content.ComponentName;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by stevenjoy on 3/13/17.
 */

public class MyReservationTest {

    @Rule
    public IntentsTestRule<LoginActivity> nLoginRule =
            new IntentsTestRule<LoginActivity>(LoginActivity.class);
    @Test
    public void testReservation() throws Exception {

        onView(withId(R.id.et_Username)).perform(typeText("dpike15")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.et_Password)).perform(typeText("test")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.b_Login)).perform(click());
        //intended(hasComponent(new ComponentName(getTargetContext(), UserActivity.class)));

        onView(withId(R.id.bMyReservation)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), MyReservationActivity.class)));
    }

}
