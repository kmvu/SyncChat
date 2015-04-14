package com.pearson.pegasus.syncChat.Home;

import com.pearson.pegasus.syncChat.library.common.Common;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

/**
 * Created by KhangVu on 4/13/15.
 */
public class SC_verifyHomePage extends Common {

    @Test
    public void testLoginAsPublisher() throws InterruptedException {
        HomeCommon.loginAsPublisherFromHome();

        if (Common.isElementPresent("//span[contains(., 'My Courses and Testbanks')]")) {
            System.out.println("Successfully located!");
        } else {
            System.out.println("Nope!");
        }
    }

    @AfterTest
    public void tearDown() {
        Common.closeBrowser();
    }
}
