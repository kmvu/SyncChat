package com.pearson.pegasus.syncChat.Home;

import com.pearson.pegasus.syncChat.library.common.Common;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

/**
 * Created by KhangVu on 5/15/15.
 */
public class SC_Subscriber {

    private SoftAssert softAssert = new SoftAssert();

    private String environment = System.getProperty("environment");
    private String browser = System.getProperty("browser");

    private final String subscriberAccount_prod = "chaos_avchat_stud_1";
    private final String subscriberAccount_staging = "peg_ppe_hed_core_stud_2";

    private final String chatRoom_locator_stg = "chaos_avchat_stud_2 chaos_avchat_stud_2')]";
    private final String chatRoom_locator_prod = "PEG_PPE_HED_Core_Stud_1 PEG_PPE_HED_Core_Stud_1";

    private String subscriberAccount;

    @BeforeTest
    public void setup() {
        if (this.environment.equals("staging")) {
            subscriberAccount = subscriberAccount_staging;
            Common.setUpVLO(this.browser, "http://mylabs.px.ppe.pearsoncmg.com/");
        } else if (this.environment.equals("production")) {
            subscriberAccount = subscriberAccount_prod;
            Common.setUpVLO(this.browser, "http://mylabs.px.pearsoned.com/Pegasus/frmLogin.aspx?logout=1&s=3");
        }
    }

    @Test
    public void testLoginAsSubscriberAndJoinChatRoom() throws InterruptedException {
        /* Login as another subscriber to join the created room chat */
        HomeCommon.subscriberLogin(subscriberAccount, softAssert);

        /* Select the video by selecting the dropdown menu */
        Common.switchToFrame(1);
        Common.clickAndWait(HomeConstants.HomePage.DROPDOWN_VIDEO_MENU.byLocator());

        Common.popUpSwitch(HomeConstants.HomePage.OPEN_DROPDOWN_ITEM.byLocator());

        Thread.sleep(10000);

        if (Common.isElementPresent(HomeConstants.HomePage.WELCOME_TITLE.byLocator())) {
            ArrayList<String> array = new ArrayList<String>(Common.driver.getWindowHandles());
            System.out.println("Current list of windows: " + array.get(array.size() - 1)); // this is to print out the list of current windows

            Common.clickAndWait(HomeConstants.HomePage.JOIN_SUBSCRIBER.byLocator());

            // Waiting for the list of created rooms to display and select the appropriate one
            String tempLoc = "";
            if (System.getProperty("environment").equals("production")) {
                tempLoc = "//td[contains(., "+ chatRoom_locator_prod + ")]";
            } else if (System.getProperty("environment").equals("staging")) {
                tempLoc = "//td[contains(., "+ chatRoom_locator_stg + ")]";
            }

            Common.waitForElementPresent(tempLoc);
            Common.clickAndWait(tempLoc + "//following-sibling::td/input");
        } else {
            System.out.println("\nCannot open Chat room for some reason! Please try again!");
        }
        Thread.sleep(2000);
        Common.clickAndWait(HomeConstants.HomePage.JOIN_CREATE_BTN.byLocator());

        // Need to click OK button in Staging environment
        if (this.environment.equals("staging"))
            Common.clickAndWait(HomeConstants.HomePage.OK_BTN.byLocator());

        Thread.sleep(2000);

        /**************************************************************************************
         * Wendy - the following is for your reference, if you want to use the invite functionality.
         **************************************************************************************/

        /* Waiting for Subscriber to login */
//        String currentWindow = Common.driver.getWindowHandle(); // get the handle for current popup
//        HomeCommon.subscriberLogin(softAssert);
//
//        /* Invite Subscriber and waiting for acceptance */
//        Common.switchToWindow(currentWindow);
//        Common.clickAndWait("//div[@id='buddies']//div[contains(., '" + anotherUser + "')]/../following-sibling::div//input");
//        Common.clickAndWait(HomeConstants.HomePage.INVITE_BTN.byLocator());
//        Thread.sleep(4000);
//
//        HomeCommon.subscriberAcceptInvitation();
    }
}
