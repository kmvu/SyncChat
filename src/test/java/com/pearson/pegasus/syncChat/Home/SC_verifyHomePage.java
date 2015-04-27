package com.pearson.pegasus.syncChat.Home;

import com.pearson.pegasus.syncChat.library.common.Common;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Created by KhangVu on 4/13/15.
 */
public class SC_verifyHomePage extends Common {

    public HomeCommon homeCommon = new HomeCommon();
    private SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void setup() {
        Common.setUpVLO("*firefox", "http://mylabs.px.pearsoned.com/Pegasus/frmLogin.aspx?logout=1&s=3");
    }

    @Test
    public void testLoginAsHostPublisher() throws InterruptedException {
        HomeCommon.loginAsPublisherFromHome("chaos_avchat_stud_1");
        HomeCommon.setupBeforeVideoChat(softAssert);
    }

    @AfterTest
    public void tearDown() {
        Common.closeBrowser();
        softAssert.assertAll();
    }
}
