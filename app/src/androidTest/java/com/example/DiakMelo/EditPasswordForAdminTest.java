package com.example.DiakMelo;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.SmallTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@SmallTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class EditPasswordForAdminTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> activityTestRule = new ActivityScenarioRule<>(LoginActivity.class);
    @Test
    public void EditPasswordTest2() {
        onView(withId(R.id.login_email)).perform(typeText("admin@admin.com"), closeSoftKeyboard());
        onView(withId(R.id.login_password)).perform(typeText("adminadmin"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(scrollTo()).perform(click());
        onView(withId(R.id.editPasswordbutton)).perform(click());
        onView(withId(R.id.edit_password)).perform(typeText("adminadmin"), closeSoftKeyboard());
        onView(withId(R.id.edit_repassword)).perform(typeText("adminadmin"), closeSoftKeyboard());
        onView(withId(R.id.editbutton)).perform(scrollTo()).perform(click());
    }
    @Test
    public void EditPasswordTest1() {
        onView(withId(R.id.login_email)).perform(typeText("admin@admin.com"), closeSoftKeyboard());
        onView(withId(R.id.login_password)).perform(typeText("adminadmin"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(scrollTo()).perform(click());
        onView(withId(R.id.editPasswordbutton)).perform(click());
        onView(withId(R.id.edit_password)).perform(typeText("adminadmin"), closeSoftKeyboard());
        onView(withId(R.id.edit_repassword)).perform(typeText("asdasd"), closeSoftKeyboard());
        onView(withId(R.id.editbutton)).perform(scrollTo()).perform(click());
        onView(withText("Nem egyezik a két Jelszó")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
}
