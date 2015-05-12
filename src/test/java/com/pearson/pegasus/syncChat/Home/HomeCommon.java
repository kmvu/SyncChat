package com.pearson.pegasus.syncChat.Home;

import com.pearson.pegasus.syncChat.library.common.Common;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

/**
 * Created by KhangVu on 4/13/15.
 */
public class HomeCommon extends Common {

    private static final String password = "p@ssw0rd";

    public static void loginFromHome(String username) throws InterruptedException {
    	String username_txtbox_locator = HomeConstants.HomePage.USERNAME_TXTBOX.byLocator();
        String password_txtbox_locator = HomeConstants.HomePage.PASSWORD_TXTBOX.byLocator();
        String submit_button_locator = HomeConstants.HomePage.SUBMIT_BTN.byLocator();

        Common.waitForElementPresent(submit_button_locator);

        /*
         * Login : 1/ Type in Username
         *         2/ Type in Password
         *         3/ Click Submit
         */
        Common.waitAndType(username_txtbox_locator, username);
        Thread.sleep(1000);
        Common.waitAndType(password_txtbox_locator, password);
        Thread.sleep(1000);
        Common.clickAndWait(submit_button_locator);
        Thread.sleep(5000);
    }

    public static SoftAssert setupBeforeVideoChat(SoftAssert softAssert) {
        /* Checking login success */
        boolean titlePresent = isElementPresent(HomeConstants.HomePage.HOME_TITLE.byLocator());
        softAssert.assertTrue(titlePresent, "Login not successfull!");

        /* Choosing the course */
        if (System.getProperty("environment").equals("staging")) {
            Common.clickAndWait(HomeConstants.HomePage.COURSE_TITLE_STG.byLocator());
        } else if (System.getProperty("environment").equals("production")) {
            Common.clickAndWait(HomeConstants.HomePage.COURSE_TITLE_PROD.byLocator());
        }

        Common.clickAndWait(HomeConstants.HomePage.ASSIGNMENT_TAB.byLocator());

        /* Make sure user clicked on Assignments link and currently located in "To do" tab */
        Common.clickAndWait(HomeConstants.HomePage.TODO_TAB.byLocator());

        return softAssert;
    }

    public static void submitForGrading() {
        Common.clickAndWait(HomeConstants.HomePage.GRADING_BTN.byLocator());
        Common.clickAndWait(HomeConstants.HomePage.FINISH_BTN.byLocator());
        Common.clickAndWait(HomeConstants.HomePage.RETURN_TO_COURSE.byLocator());
    }

    public static ArrayList<String> waitAndAcceptInvitation() throws InterruptedException {
        Thread.sleep(15000);
        return Common.invitationHandler(true);
    }

    public static ArrayList<String> waitAndDenyInvitation() throws InterruptedException {
        Thread.sleep(15000);
        return Common.invitationHandler(false);
    }

    /* Subscriber actions */
    public static SoftAssert subscriberLogin(String subscriberAccount, SoftAssert softAssert) throws InterruptedException {
        /* Open a new session for Subscriber */
        if (System.getProperty("environment").equals("staging")) {
            Common.setUpVLO(System.getProperty("browser"), "http://mylabs.px.ppe.pearsoncmg.com/");
        } else if (System.getProperty("environment").equals("production")) {
            Common.setUpVLO(System.getProperty("browser"), "http://mylabs.px.pearsoned.com/Pegasus/frmLogin.aspx?logout=1&s=3");
        }
    	
        /* Login as invited Subscriber */
        HomeCommon.loginFromHome(subscriberAccount.toLowerCase());

        return HomeCommon.setupBeforeVideoChat(softAssert);
    }

    public static void subscriberAcceptInvitation() throws InterruptedException {
        /* Accept invitation */
        ArrayList<String> currentWindowList = HomeCommon.waitAndAcceptInvitation();
        Common.popUpSwitch(currentWindowList);

        /* Join room */
        Common.clickAndWait(HomeConstants.HomePage.JOIN_CREATE_BTN.byLocator());
        Thread.sleep(5000);
    }

    public static void subscriberDenyInvitation() throws InterruptedException {
        /* Deny invitation */
        ArrayList<String> currentWindowList = HomeCommon.waitAndAcceptInvitation();
        Common.popUpSwitch(currentWindowList);

        /* Join room */
        Common.clickAndWait(HomeConstants.HomePage.JOIN_CREATE_BTN.byLocator());
        Thread.sleep(5000);
    }


}
