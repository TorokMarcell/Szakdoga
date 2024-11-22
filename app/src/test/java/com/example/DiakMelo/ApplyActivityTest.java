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
public class ApplyActivityTest {


    String mockDescreption = "mockDescreption";
    String mockStudentID = "mockStudentID";


    int mockjobid = 1;
    @Test
    public void testAllFieldNotEmpty() {
        ApplyActivity activity = Robolectric.buildActivity(ApplyActivity.class).create().get();
        boolean result = activity.allFieldNotEmpty(mockDescreption);
        ShadowLooper.runUiThreadTasks();
        assertTrue(result);
    }
    @Test
    public void testoneFieldEmpty() {
        ApplyActivity activity = Robolectric.buildActivity(ApplyActivity.class).create().get();
        boolean result = activity.allFieldNotEmpty("");
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
    @Test
    public void insertToDbtest() {
        ApplyActivity activity = Robolectric.buildActivity(ApplyActivity.class).create().get();
        boolean result = activity.insertToDb(mockStudentID,mockjobid,mockDescreption);
        ShadowLooper.runUiThreadTasks();
        assertTrue(result);
    }
    @Test
    public void insertToDbtesttWithtwoEmptyData() {
        ApplyActivity activity = Robolectric.buildActivity(ApplyActivity.class).create().get();
        boolean result = activity.insertToDb(mockStudentID,mockjobid,"");
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
    @Test
    public void insertToDbtesttWiththreeEmptyData() {
        ApplyActivity activity = Robolectric.buildActivity(ApplyActivity.class).create().get();
        boolean result = activity.insertToDb("",mockjobid,"");
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
}
