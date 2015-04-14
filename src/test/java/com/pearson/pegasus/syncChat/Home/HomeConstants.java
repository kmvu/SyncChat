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
        SUBMIT_BTN("","","//button[contains(., 'Sign In')]","");

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
