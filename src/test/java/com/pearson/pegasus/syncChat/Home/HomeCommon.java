package com.pearson.pegasus.syncChat.Home;

import com.pearson.pegasus.syncChat.library.common.Common;
import org.openqa.selenium.WebDriver;

/**
 * Created by KhangVu on 4/13/15.
 */
public class HomeCommon extends Common {

    public static Common common = new Common();
    private static String syncChatUrl = "http://mylabs.px.ppe.pearsoncmg.com";
    static WebDriver driver = common.driver;

    public static void loginAsPublisherFromHome() throws InterruptedException {
        common.openBrowser(syncChatUrl);

        Thread.sleep(4000);

        String username_txtbox = HomeConstants.HomePage.USERNAME_TXTBOX.byLocator();
        String password_txtbox = HomeConstants.HomePage.PASSWORD_TXTBOX.byLocator();
        String submit_button = HomeConstants.HomePage.SUBMIT_BTN.byLocator();

        /*
         * Login : 1/ Type in Username
         *         2/ Type in Password
         *         3/ Click Submit
         */
        common.typeAndWait(username_txtbox, "peg_ppe_hed_core_stud_1");
        Thread.sleep(2000);
        common.typeAndWait(password_txtbox, "p@ssw0rd");
        Thread.sleep(2000);
        common.clickAndWait(submit_button);
        Thread.sleep(10000);
    }



}
