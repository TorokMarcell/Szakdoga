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
import static org.hamcrest.CoreMatchers.anything;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.SmallTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

@SmallTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class EditPasswordUserTest {
    @Test
    public void EditPasswordTest2() {
        onView(withId(R.id.login_email)).perform(typeText("xd@xd.com"), closeSoftKeyboard());
        onView(withId(R.id.login_password)).perform(typeText("asdasd"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(scrollTo()).perform(click());
        onView(withId(R.id.editPasswordbutton2)).perform(click());
        onView(withId(R.id.edit_password_user)).perform(typeText("xdxd"), closeSoftKeyboard());
        onView(withId(R.id.edit_repassword_user)).perform(typeText("xdxd"), closeSoftKeyboard());
        onView(withId(R.id.editbutton_user)).perform(scrollTo()).perform(click());
    }
    @Test
    public void EditPasswordTest1() {
        onView(withId(R.id.login_email)).perform(typeText("xd@xd.com"), closeSoftKeyboard());
        onView(withId(R.id.login_password)).perform(typeText("asdasd"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(scrollTo()).perform(click());
        onView(withId(R.id.editPasswordbutton2)).perform(click());
        onView(withId(R.id.edit_password_user)).perform(typeText("xdxd"), closeSoftKeyboard());
        onView(withId(R.id.edit_repassword_user)).perform(typeText("asdasd"), closeSoftKeyboard());
        onView(withId(R.id.editbutton_user)).perform(scrollTo()).perform(click());
        onView(withText("Nem egyezik a két Jelszó")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
}
