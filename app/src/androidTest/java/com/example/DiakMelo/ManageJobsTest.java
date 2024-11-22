package com.example.DiakMelo;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.CoreMatchers.anything;

import android.os.SystemClock;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.SmallTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@SmallTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class ManageJobsTest{
        @Rule
        public ActivityScenarioRule<LoginActivity> activityTestRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void RegisterUiTest() {
        onView(withId(R.id.login_email)).perform(typeText("asd@asd.com"), closeSoftKeyboard());
        onView(withId(R.id.login_password)).perform(typeText("asdasd"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(scrollTo()).perform(click());
        onView(withId(R.id.joblistButton)).perform(scrollTo()).perform(click());
        onData(anything()).atPosition(0).perform(click());
        SystemClock.sleep(500);
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.joblistButton)).perform(click());
        onView(withId(R.id.accept_button)).perform(scrollTo()).perform(click());
    }
    @Test
    public void RegisterUiTest2() {
        onView(withId(R.id.login_email)).perform(typeText("asd@asd.com"), closeSoftKeyboard());
        onView(withId(R.id.login_password)).perform(typeText("asdasd"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(scrollTo()).perform(click());
        onView(withId(R.id.joblistButton)).perform(scrollTo()).perform(click());
        onData(anything()).atPosition(0).perform(click());
        SystemClock.sleep(500);
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.joblistButton)).perform(click());
        onView(withId(R.id.declinebutton_button)).perform(scrollTo()).perform(click());
    }
}
