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
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.SmallTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@SmallTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class RegisterTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> activityTestRule = new ActivityScenarioRule<>(LoginActivity.class);
    @Test
    public void RegisterUiTest() {
        onView(withId(R.id.signupRedirectText)).perform(scrollTo()).perform(click());
        onView(withId(R.id.signup_firstname)).perform(typeText("asd"),closeSoftKeyboard());
        onView(withId(R.id.signup_lastname)).perform(typeText("asd"),closeSoftKeyboard());
        onView(withId(R.id.signup_email)).perform(typeText("qwert@qwert.com"),closeSoftKeyboard());
        onView(withId(R.id.signup_studentId)).perform(scrollTo()).perform(typeText("7654321090"),closeSoftKeyboard());
        onView(withId(R.id.signup_birthdate)).perform(scrollTo()).perform(typeText("2001-01-01"),closeSoftKeyboard());
        onView(withId(R.id.signup_password)).perform(scrollTo()).perform(typeText("asdasd"),closeSoftKeyboard());
        onView(withId(R.id.signup_confirm)).perform(scrollTo()).perform(typeText("asdasd"),closeSoftKeyboard());
        onView(withId(R.id.signup_button)).perform(scrollTo()).perform(scrollTo()).perform(click());
    }
    @Test
    public void RegisterUiTestWithNodata() {
        onView(withId(R.id.signupRedirectText)).perform(scrollTo()).perform(click());
        onView(withId(R.id.signup_firstname)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.signup_lastname)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.signup_email)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.signup_studentId)).perform(scrollTo()).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.signup_birthdate)).perform(scrollTo()).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.signup_password)).perform(scrollTo()).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.signup_confirm)).perform(scrollTo()).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.signup_button)).perform(scrollTo()).perform(scrollTo()).perform(click());
        onView(withText("Kérlek Töltsd ki az összes mezőt.")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
    @Test
    public void RegisterUiTestWithWrongFirtsname() {
        onView(withId(R.id.signupRedirectText)).perform(scrollTo()).perform(click());
        onView(withId(R.id.signup_firstname)).perform(typeText("1"),closeSoftKeyboard());
        onView(withId(R.id.signup_lastname)).perform(typeText("asd"),closeSoftKeyboard());
        onView(withId(R.id.signup_email)).perform(typeText("qwert@qwert.com"),closeSoftKeyboard());
        onView(withId(R.id.signup_studentId)).perform(scrollTo()).perform(typeText("7654321090"),closeSoftKeyboard());
        onView(withId(R.id.signup_birthdate)).perform(scrollTo()).perform(typeText("2001-01-01"),closeSoftKeyboard());
        onView(withId(R.id.signup_password)).perform(scrollTo()).perform(typeText("asdasd"),closeSoftKeyboard());
        onView(withId(R.id.signup_confirm)).perform(scrollTo()).perform(typeText("asdasd"),closeSoftKeyboard());
        onView(withId(R.id.signup_button)).perform(scrollTo()).perform(scrollTo()).perform(click());
        onView(withText("Kérlek ide csak betűt írj")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
    @Test
    public void RegisterUiTestWithWrongEmail() {
        onView(withId(R.id.signupRedirectText)).perform(scrollTo()).perform(click());
        onView(withId(R.id.signup_firstname)).perform(typeText("asd"),closeSoftKeyboard());
        onView(withId(R.id.signup_lastname)).perform(typeText("asd"),closeSoftKeyboard());
        onView(withId(R.id.signup_email)).perform(typeText("qwertqwert.com"),closeSoftKeyboard());
        onView(withId(R.id.signup_studentId)).perform(scrollTo()).perform(typeText("7654321090"),closeSoftKeyboard());
        onView(withId(R.id.signup_birthdate)).perform(scrollTo()).perform(typeText("2001-01-01"),closeSoftKeyboard());
        onView(withId(R.id.signup_password)).perform(scrollTo()).perform(typeText("asdasd"),closeSoftKeyboard());
        onView(withId(R.id.signup_confirm)).perform(scrollTo()).perform(typeText("asdasd"),closeSoftKeyboard());
        onView(withId(R.id.signup_button)).perform(scrollTo()).perform(scrollTo()).perform(click());
        onView(withText("Nem helyes formátumban adtad meg az emailed")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
    @Test
    public void RegisterUiTestWithShortStudentID() {
        onView(withId(R.id.signupRedirectText)).perform(scrollTo()).perform(click());
        onView(withId(R.id.signup_firstname)).perform(typeText("asd"),closeSoftKeyboard());
        onView(withId(R.id.signup_lastname)).perform(typeText("asd"),closeSoftKeyboard());
        onView(withId(R.id.signup_email)).perform(typeText("qwert@qwert.com"),closeSoftKeyboard());
        onView(withId(R.id.signup_studentId)).perform(scrollTo()).perform(typeText("765432109"),closeSoftKeyboard());
        onView(withId(R.id.signup_birthdate)).perform(scrollTo()).perform(typeText("2001-01-01"),closeSoftKeyboard());
        onView(withId(R.id.signup_password)).perform(scrollTo()).perform(typeText("asdasd"),closeSoftKeyboard());
        onView(withId(R.id.signup_confirm)).perform(scrollTo()).perform(typeText("asdasd"),closeSoftKeyboard());
        onView(withId(R.id.signup_button)).perform(scrollTo()).perform(scrollTo()).perform(click());
        onView(withText("10 karakterből kell áljon az azonosítód")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
    @Test
    public void RegisterUiTestWithWrongConfirmPassword() {
        onView(withId(R.id.signupRedirectText)).perform(scrollTo()).perform(click());
        onView(withId(R.id.signup_firstname)).perform(typeText("asd"),closeSoftKeyboard());
        onView(withId(R.id.signup_lastname)).perform(typeText("asd"),closeSoftKeyboard());
        onView(withId(R.id.signup_email)).perform(typeText("qwert@qwert.com"),closeSoftKeyboard());
        onView(withId(R.id.signup_studentId)).perform(scrollTo()).perform(typeText("765432109"),closeSoftKeyboard());
        onView(withId(R.id.signup_birthdate)).perform(scrollTo()).perform(typeText("20010101"),closeSoftKeyboard());
        onView(withId(R.id.signup_password)).perform(scrollTo()).perform(typeText("asdasd"),closeSoftKeyboard());
        onView(withId(R.id.signup_confirm)).perform(scrollTo()).perform(typeText("asdasd2"),closeSoftKeyboard());
        onView(withId(R.id.signup_button)).perform(scrollTo()).perform(scrollTo()).perform(click());
        onView(withText("Kérlek így add meg a születési dátumod: 2000-01-01")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
    @Test
    public void RegisterUiTestWithWrongBirthDate() {
        onView(withId(R.id.signupRedirectText)).perform(scrollTo()).perform(click());
        onView(withId(R.id.signup_firstname)).perform(typeText("asd"),closeSoftKeyboard());
        onView(withId(R.id.signup_lastname)).perform(typeText("asd"),closeSoftKeyboard());
        onView(withId(R.id.signup_email)).perform(typeText("qwert@qwert.com"),closeSoftKeyboard());
        onView(withId(R.id.signup_studentId)).perform(scrollTo()).perform(typeText("765432109"),closeSoftKeyboard());
        onView(withId(R.id.signup_birthdate)).perform(scrollTo()).perform(typeText("20010101"),closeSoftKeyboard());
        onView(withId(R.id.signup_password)).perform(scrollTo()).perform(typeText("asdasd"),closeSoftKeyboard());
        onView(withId(R.id.signup_confirm)).perform(scrollTo()).perform(typeText("asdasd"),closeSoftKeyboard());
        onView(withId(R.id.signup_button)).perform(scrollTo()).perform(scrollTo()).perform(click());
        onView(withText("A két jelszó nem egyezik meg")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

}
