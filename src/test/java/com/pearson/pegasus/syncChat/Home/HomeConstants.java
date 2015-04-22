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
         * Course title
         */
        COURSE_TITLE("", "", "//a[contains(., ' Admin PE AVChat Section 1')]", ""),
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
         * "Join Room" button to join created room in popup window
         */
        JOIN_BTN("", "", "//input[@id='joinRoom']", ""),
        /**
         * "OK" button to ask for permission
         */
        OK_BTN("", "", "//div[@class='modal-footer']//a", ""),

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
