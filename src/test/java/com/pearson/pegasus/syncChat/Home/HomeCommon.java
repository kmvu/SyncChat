package com.pearson.pegasus.syncChat.Home;

import com.pearson.pegasus.syncChat.library.common.Common;

/**
 * Created by KhangVu on 4/13/15.
 */
public class HomeCommon extends Common {

    private static final String username = "peg_ppe_hed_core_stud_1";
    private static final String password = "p@ssw0rd";
    private static final String syncChatUrl = "http://mylabs.px.ppe.pearsoncmg.com";

    public static void loginAsPublisherFromHome() throws InterruptedException {
//        Common.openBrowser(syncChatUrl);

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



}
