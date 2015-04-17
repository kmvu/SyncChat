package com.pearson.pegasus.syncChat.Home;

import com.pearson.pegasus.syncChat.library.common.Common;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Created by KhangVu on 4/13/15.
 */
public class SC_verifyHomePage extends Common {

    public HomeCommon homeCommon = new HomeCommon();
    private SoftAssert softAssert = new SoftAssert();

    @Test
    public void testLoginAsPublisher() throws InterruptedException {
        HomeCommon.loginAsPublisherFromHome();

        /* Checking login success */
        boolean titlePresent = homeCommon.isElementPresent("//span[contains(text(), 'My Courses and Testbanks')]");
        softAssert.assertTrue(titlePresent, "Login not successfull!");

        Common.dropdownMenu("//a[@class='CourseCMenue']", "open");
    }

    @AfterTest
    public void tearDown() {
        Common.closeBrowser();
    }
}
