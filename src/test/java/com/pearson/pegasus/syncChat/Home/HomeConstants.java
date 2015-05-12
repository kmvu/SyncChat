package com.pearson.pegasus.syncChat.Home;

/**
 * Created by KhangVu on 4/13/15.
 */
public class HomeConstants {

    public enum HomePage {
        /**
         * Username textbox
         */
        USERNAME_TXTBOX("","","//input[@id='loginname']",""),
        /**
         * Password textbox
         */
        PASSWORD_TXTBOX("","","//input[@id='password']",""),
        /**
         * Password textbox
         */
        SUBMIT_BTN("","","//button[contains(., 'Sign In')]",""),
        /**
         * Home title
         */
        HOME_TITLE("", "", "//span[contains(text(), 'My Courses and Testbanks')]", ""),
        /**
         * Course title in production
         */
        COURSE_TITLE_PROD("", "", "//a[contains(., 'Chaos Course1 PE AVChat Section 1')]", ""),
        /**
         * Course title in staging
         */
        COURSE_TITLE_STG("", "", "//a[contains(., 'Admin PE AVChat Section 1')]", ""),
        /**
         * Assignment tab
         */
        ASSIGNMENT_TAB("", "", "//span[contains(., 'Assignments')]", ""),
        /**
         * To do Tab
         */
        TODO_TAB("", "", "//a[contains(., 'To Do')]", ""),
        /**
         * Dropdown menu for video chat
         */
        DROPDOWN_VIDEO_MENU("", "", "//div[@class='ToDoCmenu']", ""),
        /**
         * "Open" item in dropdown video menu
         */
        OPEN_DROPDOWN_ITEM("", "", "//div[@id='PegMenu32']//span", ""),
        /**
         * Create button
         */
        CREATE_BTN("", "", "//input[@value='Create a new chat room']", ""),
        /**
         * "Join Room" button to join created room in popup window
         */
        JOIN_CREATE_BTN("", "", "//input[@id='joinRoom']", ""),
        /**
         * "OK" button to ask for permission
         */
        OK_BTN("", "", "//div[@class='modal-footer']//a", ""),
        /**
         * Invite Users
         */
        INVITE_BTN("", "", "//div[@id='InviteUsers']", ""),
        /**
         * Invitation Accept button
         */
        ACCEPT_BTN("", "", "//a[@id='imgOk']//span", ""),
        /**
         * Invitation Deny button
         */
        DENY_BTN("", "", "//a[@id='imgCancel']//span", ""),
        /**
         * Chat room welcome title
         */
        WELCOME_TITLE("", "", "//h5[contains(., 'Welcome to AVChat Assessment')]", ""),
        /**
         * Join existing chat room as subscriber
         */
        JOIN_SUBSCRIBER("", "", "//input[@value='Join an existing chat room']", ""),
        /**
         * Record button (with red circle)
         */
        RECORD_BTN("", "", "//div[@id='recordButton']", ""),
        /**
         * Button to submit for grading
         */
        GRADING_BTN("", "", "//span[contains(., 'FINISH: Submit for Grading')]", ""),
        /**
         * Finish button
         */
        FINISH_BTN("", "", "//span[contains(., 'Finish')]", ""),
        /**
         * Button to return to course
         */
        RETURN_TO_COURSE("", "", "//span[contains(., 'Return to Course')]", ""),

        ;
        private  String id;
        private  String cssPath;
        private  String xPath;
        private  String label;

        private HomePage(String idLoc, String cssPathLoc, String xPathLoc, String labelLoc) {
            id = idLoc;
            cssPath = cssPathLoc;
            xPath = xPathLoc;
            label = labelLoc;
        }

        public String byLocator() {
            if(id.isEmpty()) {
                if(cssPath.isEmpty()) {
                    if(label.isEmpty()) {
                        return(xPath);
                    } else{
                        return(label);
                    }
                } else {
                    return(cssPath);
                }
            } else {
                return(id);
            }
        }
    }


}
