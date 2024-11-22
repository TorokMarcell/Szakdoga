package com.example.DiakMelo;

import static android.app.PendingIntent.getActivity;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.SmallTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.MonitoringInstrumentation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@SmallTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class LoginTest extends MonitoringInstrumentation {
    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);


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
