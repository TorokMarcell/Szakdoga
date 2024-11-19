package com.example.szakdoghozkell;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.widget.Toast;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLooper;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 33)
public class AddjobActivityTest {
    String mockTittle = "mockTitle";
    String mockDescreption = "mockDescreption";
    String mockSalary = "mockSalary";
    String mockLocation = "mockLocation";

    @Test
    public void testAllFieldNotEmpty() {
        AddJobsActivity activity = Robolectric.buildActivity(AddJobsActivity.class).create().get();
        boolean result = activity.allFieldNotEmpty(mockTittle,mockDescreption,mockSalary,mockLocation);
        ShadowLooper.runUiThreadTasks();
        assertTrue(result);
    }
    @Test
    public void testoneFieldEmpty() {
        AddJobsActivity activity = Robolectric.buildActivity(AddJobsActivity.class).create().get();
        boolean result = activity.allFieldNotEmpty("",mockDescreption,mockSalary,mockLocation);
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
    @Test
    public void testtwoFieldEmpty() {
        AddJobsActivity activity = Robolectric.buildActivity(AddJobsActivity.class).create().get();
        boolean result = activity.allFieldNotEmpty("","","",mockLocation);
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
    @Test
    public void testthreeFieldEmpty() {
        AddJobsActivity activity = Robolectric.buildActivity(AddJobsActivity.class).create().get();
        boolean result = activity.allFieldNotEmpty("","","",mockLocation);
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
    @Test
    public void insertToDbtest() {
        AddJobsActivity activity = Robolectric.buildActivity(AddJobsActivity.class).create().get();
        boolean result = activity.insertToDb(mockTittle,mockDescreption,11,mockLocation,1);
        ShadowLooper.runUiThreadTasks();
        assertTrue(result);
    }
    @Test
    public void insertToDbtestWithoneEmptyData() {
        AddJobsActivity activity = Robolectric.buildActivity(AddJobsActivity.class).create().get();
        boolean result = activity.insertToDb("",mockDescreption,11,mockLocation,1);
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
    @Test
    public void insertToDbtesttWithtwoEmptyData() {
        AddJobsActivity activity = Robolectric.buildActivity(AddJobsActivity.class).create().get();
        boolean result = activity.insertToDb("","",11,mockLocation,1);
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
    @Test
    public void insertToDbtesttWiththreeEmptyData() {
        AddJobsActivity activity = Robolectric.buildActivity(AddJobsActivity.class).create().get();
        boolean result = activity.insertToDb("","",11,"",1);
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
}