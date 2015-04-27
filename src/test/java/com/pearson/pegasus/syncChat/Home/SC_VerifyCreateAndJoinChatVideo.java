package com.pearson.pegasus.syncChat.Home;

import com.pearson.pegasus.syncChat.library.common.Common;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Created by KhangVu on 4/26/15.
 */
public class SC_VerifyCreateAndJoinChatVideo extends Common {

    public HomeCommon homeCommon = new HomeCommon();
    private SoftAssert softAssert = new SoftAssert();

    private String anotherUser = "PEG_PPE_HED_Core_Stud_3";

    @Test
    public void testOpenVideoChatByHost() throws InterruptedException {
        /* Select the video by selecting the dropdown menu */
        Common.switchToFrame(1);
        Common.clickAndWait(HomeConstants.HomePage.DROPDOWN_VIDEO_MENU.byLocator());

        /* 1/ Switch window handle to pop-up video chat window
         * 2/ Wait click on "Create Room" button to present
         * 3/ Wait and click to confirm permission to share video */
        Common.popUpSwitch(HomeConstants.HomePage.OPEN_DROPDOWN_ITEM.byLocator());

//        if (Common.isElementPresent(HomeConstants.HomePage.CREATE_BTN.byLocator())) // wait to make sure we can click to create room
//            Common.clickAndWait(HomeConstants.HomePage.CREATE_BTN.byLocator());

        Common.clickAndWait(HomeConstants.HomePage.JOIN_CREATE_BTN.byLocator());
        Common.clickAndWait(HomeConstants.HomePage.OK_BTN.byLocator());
        Thread.sleep(2000);

        Common.clickAndWait("//div[@id='buddies']//div[contains(., '" + anotherUser +"')]/../following-sibling::div//input");
        Common.clickAndWait(HomeConstants.HomePage.INVITE_BTN.byLocator());
        Thread.sleep(4000);
    }

    @Test(dependsOnMethods = "testOpenVideoChatByHost")
    public void testLogAnotherUserIn() throws InterruptedException {
        Common.setUpVLO("*firefox", "http://mylabs.px.ppe.pearsoncmg.com");

        homeCommon.loginAsPublisherFromHome(anotherUser.toLowerCase());
        homeCommon.setupBeforeVideoChat(softAssert);

        homeCommon.waitAndAcceptInvitation();
        homeCommon.popUpSwitch();

        Common.clickAndWait(HomeConstants.HomePage.JOIN_CREATE_BTN.byLocator());
        Common.clickAndWait(HomeConstants.HomePage.OK_BTN.byLocator());
        Thread.sleep(2000);
    }

    @AfterTest
    public void tearDown() {
        softAssert.assertAll();
    }

}
