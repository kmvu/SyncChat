package com.pearson.pegasus.syncChat.Home;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pearson.pegasus.syncChat.library.common.Common;

/**
 * Created by KhangVu on 4/13/15.
 */
public class SC_Publisher extends Common {

    private SoftAssert softAssert = new SoftAssert();

    private final String publisherAccount_prod = "chaos_avchat_stud_2";
    private final String publisherAccount_staging = "peg_ppe_hed_core_stud_1";

    private String publisherAccount;

    private String environment = System.getProperty("environment");
    private String browser = System.getProperty("browser");

    @BeforeTest
    public void setup() {
        if (this.environment.equals("staging")) {
            publisherAccount = publisherAccount_staging;
            Common.setUpVLO(this.browser, "http://mylabs.px.ppe.pearsoncmg.com/");
        } else if (this.environment.equals("production")) {
            publisherAccount = publisherAccount_prod;
            Common.setUpVLO(this.browser, "http://mylabs.px.pearsoned.com/Pegasus/frmLogin.aspx?logout=1&s=3");
        }
    }

    /**
     * Login as a publisher
     * @throws InterruptedException
     */
    @Test
    public void testLoginAsPublisher() throws InterruptedException {
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
        System.out.println("Current list of windows: " + array.get(array.size() - 1));

        Common.clickAndWait(HomeConstants.HomePage.JOIN_CREATE_BTN.byLocator());

        // Need to click OK button in Staging environment
        if (this.environment.equals("staging"))
            Common.clickAndWait(HomeConstants.HomePage.OK_BTN.byLocator());

        Thread.sleep(20000); // waiting for Subscriber to login and join the classroom
    }

    /**
     * This test method will get called whenever Subscriber finishes login in and join the room
     * @throws InterruptedException
     */
    @Test(dependsOnMethods = "testLoginAsPublisher")
    public void testRecordingAndSubmitForGrading() throws InterruptedException {
        /* After joining the room chat with Publisher:
         * 1 - Click on the "Record button" to record
         * 2 - Waiting for 10 seconds to recording
         * 3 - Submit for grading */

        Common.clickAndWait(HomeConstants.HomePage.RECORD_BTN.byLocator());

        Thread.sleep(10000);

        HomeCommon.submitForGrading();
    }

    @AfterTest
    public void tearDown() {
        Common.closeBrowser();
        softAssert.assertAll();
    }
}
