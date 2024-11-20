package com.example.szakdoghozkell;

import static android.app.PendingIntent.getActivity;
import static androidx.core.content.ContextCompat.startActivity;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;

import static java.util.function.Predicate.not;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.SmallTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.MonitoringInstrumentation;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
@SmallTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class MyStepdefs  extends MonitoringInstrumentation {
    @Rule
    public ActivityTestRule<LgoinActivity> activityTestRule = new ActivityTestRule<>(LgoinActivity.class);


    @Test
    public void I_have_a_login_activity() {
        assertNotNull(activityTestRule);
    }
    @Test
    public void LoginUiTest() {
        onView(withId(R.id.login_email)).perform(typeText("xd@xd.com"),closeSoftKeyboard());
        onView(withId(R.id.login_password)).perform(typeText("asdasd"),closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(scrollTo()).perform(click());
    }
    @Test
    public void LoginUiTestWrongDatas() {
        onView(withId(R.id.login_email)).perform(typeText("wrong data"),closeSoftKeyboard());
        onView(withId(R.id.login_password)).perform(typeText("wrong data"),closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(scrollTo()).perform(click());
        onView(withText("Rossz adatokat adtál meg")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
    @Test
    public void LoginUiTestNoDatas() {
        onView(withId(R.id.login_button)).perform(scrollTo()).perform(click());
        onView(withText("Kérlek töltsd ki az összes mezőt")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
    @Test
    public void ValidateUiTest() {

    }
}
