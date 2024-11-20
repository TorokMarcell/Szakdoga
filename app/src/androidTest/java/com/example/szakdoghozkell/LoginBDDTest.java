package com.example.szakdoghozkell;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import android.os.SystemClock;


import static org.junit.Assert.assertNotNull;

import android.content.Intent;

import androidx.test.filters.SmallTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.runner.RunWith;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class LoginBDDTest {
//    @Rule
//    public ActivityTestRule<LgoinActivity> activityTestRule = new ActivityTestRule<>(LgoinActivity.class);
//
//    @Rule
//    public LgoinActivity activity;
//
//
//    @Before("@login-feature")
//    public void setup() {
//        activityTestRule.launchActivity(new Intent());
//        activity = activityTestRule.getActivity();
//    }
//    @After("@login-feature")
//    public void tearDown() {
//        activityTestRule.finishActivity();
//    }
//    @Given("^létezik a login felület")
//    public void I_have_a_login_activity() {
//        assertNotNull(activity);
//    }
//    @And("^rámegyek a bejelentkezés gombra$")
//    public void i_press_button() {
//        Intent intent = new Intent(activity, MainActivity.class);
//    }
//
//    @And("^beírom a jelszavam \"([^\"]*)\"$")
//    public void beíromAJelszavam(String jelszo) throws Throwable {
//        onView(withId(R.id.login_password)).perform(typeText(jelszo), closeSoftKeyboard());
//        throw new PendingException();
//    }
//
//    @When("^beírom az emailem \"([^\"]*)\"$")
//    public void beíromAzEmailem(String email) throws Throwable {
//        onView(withId(R.id.login_email)).perform(typeText(email));
//        throw new PendingException();
//    }
//
//    @Then("^a validáció menüben találom magam$")
//    public void aValidációMenübenTalálomMagam() {
//        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//    }
}
