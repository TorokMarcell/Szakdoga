package com.example.szakdoghozkell;

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
public class LgoinActivityTest {

    String mockEmail = "mockEmail";
    String mockPassword = "mockPassword";

    @Test
    public void testAllFieldNotEmpty() {
        LgoinActivity activity = Robolectric.buildActivity(LgoinActivity.class).create().get();
        boolean result = activity.allFieldNotEmpty(mockEmail,mockPassword);
        ShadowLooper.runUiThreadTasks();
        assertTrue(result);
    }
    @Test
    public void testoneFieldEmpty() {
        LgoinActivity activity = Robolectric.buildActivity(LgoinActivity.class).create().get();
        boolean result = activity.allFieldNotEmpty("",mockPassword);
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
    @Test
    public void testoneFieldTwoEmpty() {
        LgoinActivity activity = Robolectric.buildActivity(LgoinActivity.class).create().get();
        boolean result = activity.allFieldNotEmpty("","");
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
}
