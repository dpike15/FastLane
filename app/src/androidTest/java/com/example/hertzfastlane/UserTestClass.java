package com.example.hertzfastlane;

import android.app.Activity;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertTrue;

/**
 * Created by stevenjoy on 4/12/17.
 */

@RunWith(AndroidJUnit4.class)

public class UserTestClass {


    @Test
    public void testGet_Set_User() {
        User person = new User();
        person.setName("steve");
        person.setPassword("1234");
        assertTrue(person.getName()=="steve");
        assertTrue(person.getPassword()=="1234");

    }
}
