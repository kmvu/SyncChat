package com.pearson.pegasus.syncChat.Home;

import com.pearson.pegasus.syncChat.library.common.Common;
import org.testng.asserts.SoftAssert;

/**
 * Created by KhangVu on 4/13/15.
 */
public class HomeCommon extends Common {

    private static final String password = "p@ssw0rd";

    public static void loginAsPublisherFromHome(String username) throws InterruptedException {
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
        Common.clickAndWait(HomeConstants.HomePage.COURSE_TITLE.byLocator());
        Common.clickAndWait(HomeConstants.HomePage.ASSIGNMENT_TAB.byLocator());

        /* Make sure user clicked on Assignments link and currently located in "To do" tab */
        Common.clickAndWait(HomeConstants.HomePage.TODO_TAB.byLocator());

        return softAssert;
    }

    public static void waitAndAcceptInvitation() throws InterruptedException {
        Thread.sleep(15000);
        Common.acceptInvitation();
    }

}
