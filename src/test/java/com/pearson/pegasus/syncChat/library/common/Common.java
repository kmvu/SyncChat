package com.pearson.pegasus.syncChat.library.common;

import com.thoughtworks.selenium.SeleneseTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by KhangVu on 4/13/15.
 */
public class Common extends SeleneseTestBase {

    InputStream propertyIs;
    static InputStream regressionInp;
    public static int timeoutSec = 180;
    public static long halfMinWait = 30000;
    public static int timeOut = 15;
    public int timeoutInt = 18000;
    public static long sleep = 1000;	//Never change this value
    public static String propertyFilePath = "/data/input/Config.properties";
    public static String configPropertyFilePath = "/data/input/ConfigurationSetUp.properties";
    static String regressionFilePath, regressionStatusSheet, regressionFileOut;

    public void getProperties() throws Exception{
        propertyIs = getClass().getResourceAsStream(Common.propertyFilePath);
        Properties prop = new Properties();
        prop.load(propertyIs);
        regressionFilePath = prop.getProperty("EXECUTIONSTATUS.STATUSFILE");
        regressionStatusSheet= prop.getProperty("EXECUTIONSTATUS.STATUSFILE.SHEET");
        regressionFileOut=getClass().getResource(regressionFilePath).toString().replace("%20", " ").replace("file:","");
        regressionInp = getClass().getResourceAsStream(regressionFilePath);
    }

    public static WebDriver driver;

    /* Common methods for UI */
    public static void clickAndWait(String locator) {
        driver.findElement(By.xpath(locator)).click();
    }

    public static void typeAndWait(String locator, String content) {
        driver.findElement(By.xpath(locator)).sendKeys(content);
    }

    public static boolean isElementPresent(String locator) {
        return driver.findElement(By.xpath(locator)).isDisplayed();
    }

    public static void dropdownMenu(String locator, String optionItem) {
        WebElement element = driver.findElement(By.xpath(locator));
        Select selectionMenu = new Select(element);

        selectionMenu.selectByVisibleText(optionItem);
    }


    /* Browser interaction methods */
    public static void openBrowser(String syncChatUrl) {
        driver = new FirefoxDriver();
        driver.get(syncChatUrl);
        driver.manage().window().maximize();
    }

    public static void closeBrowser() {
        driver.close();
        driver.quit();
    }

}
