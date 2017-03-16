package com.example.hertzfastlane;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
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
    public ActivityTestRule<LoginActivity> nImageTestRule=
            new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Test
    public void test() throws Exception {
        onView(withId(R.id.et_Username)).perform(typeText("dpike15")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.et_Password)).perform(typeText("test")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.b_Login)).perform(click());
        // Type text and then press the button.
        onView(withId(R.id.home_bg)).check(matches(withDrawable(R.drawable.login_bg_dark_6)));
        //ViewInteraction check = onView(withId(R.id.home_bg)).check(matches(withId(R.drawable.login_bg_dark_6)));
    }
}
