package com.example.DiakMelo;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.SmallTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@SmallTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class SignupForJobctivityTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> activityTestRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void RegisterUiTestWithNoDescription() {
        onView(withId(R.id.login_email)).perform(typeText("qwert@qwert.com"), closeSoftKeyboard());
        onView(withId(R.id.login_password)).perform(typeText("asdasd"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(scrollTo()).perform(click());
        onView(withId(R.id.jobbforUserButton)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.apply_description)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.apply_button)).perform(scrollTo()).perform(click());
    }
    @Test
    public void RegisterUiTest() {
        onView(withId(R.id.login_email)).perform(typeText("qwert@qwert.com"), closeSoftKeyboard());
        onView(withId(R.id.login_password)).perform(typeText("asdasd"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(scrollTo()).perform(click());
        onView(withId(R.id.jobbforUserButton)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.apply_description)).perform(typeText("asdasd"), closeSoftKeyboard());
        onView(withId(R.id.apply_button)).perform(click());
        onView(withText("Erre a Munkára már jelentkeztél!")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
    @Test
    public void RegisterUiTestAlreadyRegistered() {
        onView(withId(R.id.login_email)).perform(typeText("qwert@qwert.com"), closeSoftKeyboard());
        onView(withId(R.id.login_password)).perform(typeText("asdasd"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(scrollTo()).perform(click());
        onView(withId(R.id.jobbforUserButton)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.apply_description)).perform(typeText("asdasd"), closeSoftKeyboard());
        onView(withId(R.id.apply_button)).perform(click());

    }
}
