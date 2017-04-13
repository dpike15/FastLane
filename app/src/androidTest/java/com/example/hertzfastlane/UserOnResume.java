//package com.example.hertzfastlane;
//
//import android.app.Activity;
//import android.app.Instrumentation;
//import android.content.Intent;
//import android.hardware.usb.UsbRequest;
//import android.os.Handler;
//import android.os.Looper;
//import android.support.test.annotation.UiThreadTest;
//import android.support.test.espresso.intent.rule.IntentsTestRule;
//import android.support.test.rule.ActivityTestRule;
//import android.util.Log;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//
//import static android.support.test.InstrumentationRegistry.getInstrumentation;
//import static junit.framework.Assert.assertEquals;
//
///**
// * Created by stevenjoy on 4/12/17.
// */
//
//public class UserOnResume {
//
//
//    private Handler mHandler = new Handler(Looper.getMainLooper());
//
//    @Rule
//    public IntentsTestRule activityRule = new IntentsTestRule<>(
//            UserActivity.class, false,    // initialTouchMode
//            false);  // launchActivity. False to set intent.
//
////    @Rule
////    public ActivityTestRule<UserActivity> activityRule =
////            new ActivityTestRule<UserActivity>(UserActivity.class);
//
//    @Before
//    public void setUp() {
//
//    }
//
//    @Test
//    public void testOnResume() {
//        final UserActivity testActivity = new UserActivity();
//        Member member = new Member();
//        member.setLoyaltyPoints(275);
//        member.setFirst_NM("derek");
//        Log.d("name", member.getFirst_NM());
//
//        Intent user = new Intent();
//        user.setAction(Intent.ACTION_SEND);
//        user.setType("text/plain");
////
//        user.putExtra("member", member);
//
//
//        activityRule.launchActivity(user);
//        //callActivityOnDestroy(testActivity);
//
//        //callActivityOnDestroy(testActivity);
//
////        testActivity.onRestart();
//    }
//
//
//
////        mHandler.post(new Runnable() {
////            @Override
////            public void run() {
////                testActivity.onRestart();
////
////            }
////        });
////        // user.callActivityOnResume(testActivity);
////    }
//       // sleep();
//        //assertFalse(testActivity.isPaused());
//
//    public void callActivityOnDestroy(final Activity activity){
//        getInstrumentation().runOnMainSync(new Runnable() {
//            public void run() {
//                activity.finish();
//            }
//        });
//    }
//
//    public void callActivityStart(final Activity activity){
//        getInstrumentation().runOnMainSync(new Runnable() {
//            public void run() {
//                activity.startActivity(new Intent(activity, UserActivity.class));
//            }
//        });
//    }
//}
