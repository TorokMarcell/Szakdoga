package com.example.DiakMelo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLooper;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 33)
public class LoginActivityTest {

    String mockEmail = "mockEmail";
    String mockPassword = "mockPassword";

    @Test
    public void testAllFieldNotEmpty() {
        LoginActivity activity = Robolectric.buildActivity(LoginActivity.class).create().get();
        boolean result = activity.allFieldNotEmpty(mockEmail,mockPassword);
        ShadowLooper.runUiThreadTasks();
        assertTrue(result);
    }
    @Test
    public void testoneFieldEmpty() {
        LoginActivity activity = Robolectric.buildActivity(LoginActivity.class).create().get();
        boolean result = activity.allFieldNotEmpty("",mockPassword);
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
    @Test
    public void testoneFieldTwoEmpty() {
        LoginActivity activity = Robolectric.buildActivity(LoginActivity.class).create().get();
        boolean result = activity.allFieldNotEmpty("","");
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
}
