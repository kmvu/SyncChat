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
        boolean titlePresent = homeCommon.isElementPresent(HomeConstants.HomePage.HOME_TITLE.byLocator());
        softAssert.assertTrue(titlePresent, "Login not successfull!");
    }

    @Test(dependsOnMethods="testLoginAsPublisher")
    public void testAssignmentAndVideoChatOpen() throws InterruptedException {
        /* Choosing the course */
        Common.clickAndWait(HomeConstants.HomePage.COURSE_TITLE.byLocator());
        Common.clickAndWait(HomeConstants.HomePage.ASSIGNMENT_TAB.byLocator());

        /* Make sure user clicked on Assignments link and currently located in "To do" tab */
        Common.clickAndWait(HomeConstants.HomePage.TODO_TAB.byLocator());

        /* Select the video by selecting the dropdown menu */
        Common.switchToFrame(1);
        Common.clickAndWait(HomeConstants.HomePage.DROPDOWN_VIDEO_MENU.byLocator());

        /* 1/ Switch window handle to pop-up video chat window
         * 2/ Wait click on "Create Room" button to present
         * 3/ Wait and click to confirm permission to share video */
        Common.popUpSwitch(HomeConstants.HomePage.OPEN_DROPDOWN_ITEM.byLocator());
        Common.clickAndWait(HomeConstants.HomePage.JOIN_BTN.byLocator());
        Common.clickAndWait(HomeConstants.HomePage.OK_BTN.byLocator());
        Thread.sleep(10000);
    }

    @AfterTest
    public void tearDown() {
        Common.closeBrowser();
    }
}
