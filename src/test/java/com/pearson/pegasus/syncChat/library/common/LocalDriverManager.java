package com.pearson.pegasus.syncChat.library.common;

import org.openqa.selenium.WebDriver;

/**
 * Created by KhangVu on 5/12/15.
 */
public class LocalDriverManager {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }
}
