//
//package com.example.hertzfastlane;
//
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Handler;
//import android.os.Looper;
//import android.support.test.InstrumentationRegistry;
//import android.support.test.espresso.ViewInteraction;
//import android.support.test.espresso.action.ViewActions;
//import android.support.test.espresso.intent.rule.IntentsTestRule;
//import android.support.test.filters.SmallTest;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//import android.test.ActivityUnitTestCase;
//import android.util.Log;
//import android.view.View;
//
//import com.google.zxing.Result;
//
//import org.hamcrest.Matcher;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static android.support.test.InstrumentationRegistry.getTargetContext;
//import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.action.ViewActions.click;
//import static android.support.test.espresso.action.ViewActions.typeText;
//import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.intent.Intents.intended;
//import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
//import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static junit.framework.Assert.assertEquals;
//import static junit.framework.Assert.assertTrue;
//
///**
// * Created by stevenjoy on 3/13/17.
// */
//@SmallTest
//@RunWith(AndroidJUnit4.class)
//public class QrTest {
//    // private Handler mHandler = new Handler(Looper.getMainLooper());
//
//    //  private CarActivity cars;
//    //  private Intent intent;
//
//
//    @Rule
//    public ActivityTestRule<CarActivity> nLoginRule =
//            new ActivityTestRule<CarActivity>(CarActivity.class) {
//                @Override
//                protected Intent getActivityIntent() {
//                    Context targetContext = InstrumentationRegistry.getInstrumentation()
//                            .getTargetContext();
//                    Car car = new Car();
//                    car.setVideoURL("X6BwCS90RGk");
//                    Intent result = new Intent(targetContext, CarActivity.class);
//                    result.putExtra("car", car);
//                    return result;
//                }
//            };
//
//
//    @Test
//    public void someTest() {
//        //onView(withId(R.id.background_image_view)).check(matches(withImageDrawable(R.drawable.login_bg_dark_6)));    }
//    }
//
//}
//
////    @Test
////    public void run() {
////        mHandler.post(new Runnable() {
////            @Override
////            public void run() {
////                Context targetContext = InstrumentationRegistry.getInstrumentation()
////                        .getTargetContext();
////                Car car = new Car();
////                car.setVideoURL("X6BwCS90RGk");
////
////                Intent intent = new Intent(targetContext, CarActivity.class);
////                intent.putExtra("car", car);
////
////
////
/////* Your activity is initialized and ready to go. */
////            }
////        });
////        nLoginRule.launchActivity(intent);
////
////        assertEquals(CarActivity.getCar().getVideoURL(), "X6BwCS90RGk" );
////    }
////}