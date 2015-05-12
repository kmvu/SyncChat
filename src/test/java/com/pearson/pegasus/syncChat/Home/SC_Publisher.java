package com.pearson.pegasus.syncChat.Home;

import com.pearson.pegasus.syncChat.library.common.Common;
import com.pearson.pegasus.syncChat.library.common.LocalDriverManager;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

/**
 * Created by KhangVu on 4/13/15.
 */
public class SC_Publisher extends Common {

    private SoftAssert softAssert = new SoftAssert();

    private final String publisherAccount_prod = "chaos_avchat_stud_2";
    private final String publisherAccount_staging = "peg_ppe_hed_core_stud_1";
    private final String subscriberAccount_prod = "chaos_avchat_stud_1";
    private final String subscriberAccount_staging = "peg_ppe_hed_core_stud_2";

    private String publisherAccount;
    private String subscriberAccount;

//    private String environment = System.getProperty("environment");
    private String environment = "staging";
    private String browser = System.getProperty("browser");

    private void invokeBrowser(String url) {
        System.out.println("Thread id = " + Thread.currentThread().getId());
        System.out.println("Hashcode of webDriver instance = " + LocalDriverManager.getDriver());
        WebDriver driver = LocalDriverManager.getDriver();
        driver.get(url);
        setUpVLO();
    }

    @Test
    public void testLoginAsPublisher() throws InterruptedException {
    	if (this.environment.equals("staging")) {
            publisherAccount = publisherAccount_staging;
            subscriberAccount = subscriberAccount_staging;
//            getDriver().get("http://mylabs.px.ppe.pearsoncmg.com/");
            invokeBrowser("http://mylabs.px.ppe.pearsoncmg.com/");
        } else if (this.environment.equals("production")) {
            publisherAccount = publisherAccount_prod;
            subscriberAccount = subscriberAccount_prod;
//            getDriver().get("http://mylabs.px.pearsoned.com/Pegasus/frmLogin.aspx?logout=1&s=3");
            invokeBrowser("http://mylabs.px.pearsoned.com/Pegasus/frmLogin.aspx?logout=1&s=3");
        }

        HomeCommon.loginFromHome(publisherAccount);
        HomeCommon.setupBeforeVideoChat(softAssert);

        /* Select the video by selecting the dropdown menu */
        Common.switchToFrame(1);
        Common.clickAndWait(HomeConstants.HomePage.DROPDOWN_VIDEO_MENU.byLocator());

        /* 1/ Switch window handle to pop-up video chat window
         * 2/ Wait click on "Create Room" button to present
         * 3/ Wait and click to confirm permission to share video */
        Common.popUpSwitch(HomeConstants.HomePage.OPEN_DROPDOWN_ITEM.byLocator());

        ArrayList<String> array = new ArrayList<String>(Common.driver.getWindowHandles());
        System.out.println("First: " + array.get(array.size() - 1));

        Common.clickAndWait(HomeConstants.HomePage.JOIN_CREATE_BTN.byLocator());

        // Need to click OK button in Staging environment
        if (this.environment.equals("staging"))
            Common.clickAndWait(HomeConstants.HomePage.OK_BTN.byLocator());


        Common.clickAndWait(HomeConstants.HomePage.RECORD_BTN.byLocator());
        Thread.sleep(10000);
        HomeCommon.submitForGrading();
    }

    @Test
    public void testLoginAsSubscriberAndJoinChatRoom() throws InterruptedException {
        /* Login as another subscriber to join the created room chat */
        HomeCommon.subscriberLogin(subscriberAccount, softAssert);

        /* Select the video by selecting the dropdown menu */
        Common.switchToFrame(1);
        Common.clickAndWait(HomeConstants.HomePage.DROPDOWN_VIDEO_MENU.byLocator());

        Common.popUpSwitch(HomeConstants.HomePage.OPEN_DROPDOWN_ITEM.byLocator());

        if (Common.isElementPresent(HomeConstants.HomePage.WELCOME_TITLE.byLocator())) {
            ArrayList<String> array = new ArrayList<String>(Common.driver.getWindowHandles());
            System.out.println("Second: " + array.get(array.size() - 1)); // this is to print out the list of current windows

            Common.clickAndWait(HomeConstants.HomePage.JOIN_SUBSCRIBER.byLocator());

            // Waiting for the list of created rooms to display and select the appropriate one
            String tempLoc = "";
            if (this.environment.equals("production")) {
                tempLoc = "//td[contains(., 'chaos_avchat_stud_2 chaos_avchat_stud_2')]";
            } else if (this.environment.equals("staging")) {
                tempLoc = "//td[contains(., 'PEG_PPE_HED_Core_Stud_1 PEG_PPE_HED_Core_Stud_1')]";
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

//    @Test(dependsOnMethods = "testLoginAsPublisher")
//    public void testRecordingAndSubmitForGrading() throws InterruptedException {
//        /* After joining the room chat with Publisher:
//         * 1 - Click on the "Record button" to record
//         * 2 - Waiting for 10 seconds to recording
//         * 3 - Submit for grading */
//
////    	Set<String> set = driver_old.getWindowHandles();
////    	Iterator<String> it = set.iterator();
////        String window = null;
////        while (it.hasNext()) window = it.next();
////
////        Thread.sleep(2000);
////        System.out.println("Switching back: " + window);
////        driver.switchTo().window(window); // this is where I want to switch back to the original window popup, but it doesn't work
////        Thread.sleep(5000);
//        Common.clickAndWait(HomeConstants.HomePage.RECORD_BTN.byLocator());
//
//        Thread.sleep(10000);
//
//        HomeCommon.submitForGrading();
//    }

//    @AfterMethod
//    public void tearDown() {
//        softAssert.assertAll();
//    }
}
