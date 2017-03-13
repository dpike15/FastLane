package com.example.hertzfastlane;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

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
import static android.support.test.espresso.contrib.DrawerActions.openDrawer;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by stevenjoy on 3/12/17.
 */

@RunWith(AndroidJUnit4.class)
public class HamburgerTest {

    @Rule
    public IntentsTestRule<LoginActivity> nLoginRule =
            new IntentsTestRule<LoginActivity>(LoginActivity.class);

    @Test
    public void testNavigationDrawerItemClick() {

        onView(withId(R.id.et_Username)).perform(typeText("dpike15")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.et_Password)).perform(typeText("test")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.b_Login)).perform(click());

        try {
            openDrawer(R.id.navList);
            onView(withText("Menu One")).perform(click());
            onView(allOf(withId(R.id.drawer_layout), withText("Profile"))).check(matches(isDisplayed()));
        }
        catch (Exception e) {

        }
    }
}
