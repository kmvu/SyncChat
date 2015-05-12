package com.pearson.pegasus.syncChat.Home;

import com.pearson.pegasus.syncChat.library.common.Common;
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

    private String publisherAccount;

//    private String environment = System.getProperty("environment");
    private String environment = "staging";
    private String browser = System.getProperty("browser");

    @BeforeMethod
    public void setup() {
        if (this.environment.equals("staging")) {
            publisherAccount = publisherAccount_staging;
            getDriver().get("http://mylabs.px.ppe.pearsoncmg.com/");
        } else if (this.environment.equals("production")) {
            publisherAccount = publisherAccount_prod;
            getDriver().get("http://mylabs.px.pearsoned.com/Pegasus/frmLogin.aspx?logout=1&s=3");
        }
    }

    @Test
    public void testLoginAsPublisher() throws InterruptedException {
        Thread.sleep(10000);
        HomeCommon.loginFromHome(publisherAccount);
        Thread.sleep(5000);
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

        Thread.sleep(20000); // Wait 20 secs for Subscriber to login
    }

    @Test(dependsOnMethods = "testLoginAsPublisher")
    public void testRecordingAndSubmitForGrading() throws InterruptedException {
        /* After joining the room chat with Publisher:
         * 1 - Click on the "Record button" to record
         * 2 - Waiting for 10 seconds to recording
         * 3 - Submit for grading */

//    	Set<String> set = driver_old.getWindowHandles();
//    	Iterator<String> it = set.iterator();
//        String window = null;
//        while (it.hasNext()) window = it.next();
//
//        Thread.sleep(2000);
//        System.out.println("Switching back: " + window);
//        driver.switchTo().window(window); // this is where I want to switch back to the original window popup, but it doesn't work
//        Thread.sleep(5000);
        Common.clickAndWait(HomeConstants.HomePage.RECORD_BTN.byLocator());

        Thread.sleep(10000);

        HomeCommon.submitForGrading();
    }

    @AfterMethod
    public void tearDown() {
        softAssert.assertAll();
    }
}
