package com.pearson.pegasus.syncChat.Home;

import com.pearson.pegasus.syncChat.library.common.Common;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Created by KhangVu on 4/13/15.
 */
public class SC_verifyHomePage extends Common {

<<<<<<< HEAD
    public HomeCommon homeCommon = new HomeCommon();
    private SoftAssert softAssert = new SoftAssert();

=======
>>>>>>> 819fefb23307dece4a47898fa33a02398a2d48d1
    @Test
    public void testLoginAsPublisher() throws InterruptedException {
        HomeCommon.loginAsPublisherFromHome();

<<<<<<< HEAD
        /* Checking login success */
        softAssert.assertTrue(
                homeCommon.isElementPresent("//span[contains(text(), 'My Courses and Testbanks')]"),
                "Login not successfull!");
=======
        if (Common.isElementPresent("//span[contains(., 'My Courses and Testbanks')]")) {
            System.out.println("Successfully located!");
        } else {
            System.out.println("Nope!");
        }
>>>>>>> 819fefb23307dece4a47898fa33a02398a2d48d1
    }

    @AfterTest
    public void tearDown() {
        Common.closeBrowser();
    }
}
