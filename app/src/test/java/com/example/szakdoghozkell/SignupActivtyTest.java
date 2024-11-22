package com.example.szakdoghozkell;

import static org.junit.Assert.assertEquals;
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
public class SignupActivtyTest {
    String mockEmail = "mockEmail@email.com";
    String mockEmailWrong = "mockEmail";
    String mockPassword = "mockPassword";
    String mockRePassword = "mockPassword";
    String mockBirthDate = "2001-01-01";
    String mockBirthDateWrong = "2001.01.01";
    String mockStudentID = "9876501234";
    String mockStudentIDWrong = "WRONG";
    String mockFirstname = "mockFirstname";
    String mockFirstnameWrong = "12";
    String mockLastName = "mockLastName";
    String mockLastNameWrong = "123";
    String exptectedString = "FAHEJ";
    String exptectedStringComplex = "TOBBTAGU";
    String testString = "FAHÉJ";
    String testStringComplex = "TÖBBTAGÚ";

    @Test
    public void testAllFieldNotEmpty() {
        SignupActivity activity = Robolectric.buildActivity(SignupActivity.class).create().get();
        boolean result = activity.allFieldNotEmpty(mockEmail,mockBirthDate,mockFirstname,mockLastName,mockStudentID,mockRePassword,mockPassword);
        ShadowLooper.runUiThreadTasks();
        assertTrue(result);
    }
    @Test
    public void testAllFieldOneEmpty() {
        SignupActivity activity = Robolectric.buildActivity(SignupActivity.class).create().get();
        boolean result = activity.allFieldNotEmpty("",mockBirthDate,mockFirstname,mockLastName,mockStudentID,mockRePassword,mockPassword);
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
    @Test
    public void RegexChecksTestWithGoodData() {
        SignupActivity activity = Robolectric.buildActivity(SignupActivity.class).create().get();
        boolean result = activity.RegexChecks(mockEmail,mockStudentID,mockFirstname,mockLastName,mockBirthDate);
        ShadowLooper.runUiThreadTasks();
        assertTrue(result);
    }
    @Test
    public void RegexChecksTestWitWrongEmail() {
        SignupActivity activity = Robolectric.buildActivity(SignupActivity.class).create().get();
        boolean result = activity.RegexChecks(mockEmailWrong,mockStudentID,mockFirstname,mockLastName,mockBirthDate);
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
    @Test
    public void RegexChecksTestWitWrongStudentID() {
        SignupActivity activity = Robolectric.buildActivity(SignupActivity.class).create().get();
        boolean result = activity.RegexChecks(mockEmail,mockStudentIDWrong,mockFirstname,mockLastName,mockBirthDate);
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
    @Test
    public void RegexChecksTestWitWrongFirstName() {
        SignupActivity activity = Robolectric.buildActivity(SignupActivity.class).create().get();
        boolean result = activity.RegexChecks(mockEmail,mockStudentID,mockFirstnameWrong,mockLastName,mockBirthDate);
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
    @Test
    public void RegexChecksTestWitWronglastName() {
        SignupActivity activity = Robolectric.buildActivity(SignupActivity.class).create().get();
        boolean result = activity.RegexChecks(mockEmail,mockStudentID,mockFirstname,mockLastNameWrong,mockBirthDate);
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
    @Test
    public void RegexChecksTestWitWrongDate() {
        SignupActivity activity = Robolectric.buildActivity(SignupActivity.class).create().get();
        boolean result = activity.RegexChecks(mockEmail,mockStudentID,mockFirstname,mockLastName,mockBirthDateWrong);
        ShadowLooper.runUiThreadTasks();
        assertFalse(result);
    }
    @Test
    public void ReplaceAccentsTest() {
        SignupActivity activity = Robolectric.buildActivity(SignupActivity.class).create().get();
        String result = activity.replaceAccents(testString);
        ShadowLooper.runUiThreadTasks();
        assertEquals(result,exptectedString);
    }    @Test
    public void ReplaceAccentsTestWithComplexData() {
        SignupActivity activity = Robolectric.buildActivity(SignupActivity.class).create().get();
        String result = activity.replaceAccents(testStringComplex);
        ShadowLooper.runUiThreadTasks();
        assertEquals(result,exptectedStringComplex);
    }
}
