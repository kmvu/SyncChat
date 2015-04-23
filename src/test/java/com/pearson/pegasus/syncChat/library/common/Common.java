package com.pearson.pegasus.syncChat.library.common;

import com.thoughtworks.selenium.SeleneseTestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by KhangVu on 4/13/15.
 */
public class Common extends SeleneseTestBase {

    InputStream propertyIs;
    static InputStream regressionInp;
    public static int timeoutSec = 180;
    public static long halfMinWait = 30000;
    public static int timeOut = 15;
    public static int timeoutInt = 18000;
    public static String timeout = "180000";
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

    public static void setUpVLO(String browser,String URL) {
        ProfilesIni allProfiles = new ProfilesIni();
        FirefoxProfile profile = allProfiles.getProfile("SyncChat_profile");
//        if(browser.equals("*iexplore")){
//			/*capabilities.setCapability(CapabilityType.ENABLE_PERSISTENT_HOVERING , true);
//		  capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS , true);*/
//            driver = new InternetExplorerDriver(capabilities);
//        }else if(browser.equals("*chrome")){
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--test-type");
//            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//            capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS , true);
//            System.setProperty("webdriver.chrome.driver", getClass().getResource("/data/input/chromedriver.exe").toString().replace("%20", " ").replace("file:",""));
//            driver = new ChromeDriver(capabilities);
//            System.out.println(DesiredCapabilities.chrome());
//        }else if(browser.equals("*firefox")){
            driver = new FirefoxDriver(profile);
//            FirefoxBinary binary = new FirefoxBinary(new File("/Users/KhangVu/Library/Application Support/Firefox/Profiles/zo078t5r.SyncChat_profile"));
//            FirefoxProfile profile = new FirefoxProfile();
//            WebDriver driver = new FirefoxDriver(binary, profile);
//        }
//        else if(browser.equals("*safari")){
//            capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS , true);
//            System.setProperty("webdriver.safari.driver", getClass().getResource("/data/input/safaridriver.exe").toString().replace("%20", " ").replace("file:",""));
//            driver = new ChromeDriver(capabilities);
//            System.out.println(DesiredCapabilities.chrome());
//
//        }
//        else {
//            System.out.println("Please selenium.select one of these browser:\niexplore\nfirefox\nchrome");
//            return;
//        }
        driver.get(URL);

        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Setting the default PageLoad Time out to 2 Mins
        driver.manage().timeouts().pageLoadTimeout(120 , TimeUnit.SECONDS);
        //Setting the default Script Time out(the amount of time to wait for an asynchronous script to finish execution before throwing an error) to 2 Mins
        driver.manage().timeouts().setScriptTimeout(120 ,TimeUnit.SECONDS);

        driver.manage().window().maximize();
    }

    /* Common methods for UI */
    public static void waitForPageToLoad(String timeout)
    {
        driver.manage().timeouts().implicitlyWait(Integer.parseInt("3"), TimeUnit.SECONDS);

        String browser=getBrowserversion(driver);
        if(browser.contains("chrome")){
            waitForAjax(timeOut);
        }
    }

    /**
     * wait till web page is in ready state.
     *
     * @param timeoutInSeconds
     *            Time till Web Driver waits.
     */
    private static void waitForAjax(int timeoutInSeconds) {
        try {
            if (driver instanceof JavascriptExecutor) {
                JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

                for (int i = 0; i < timeoutInSeconds; i++) {
                    boolean resultofAjaxWait = jsDriver.executeScript(
                            "return document.readyState").equals("complete");
                    Reporter.log("Ajex calls complete " + resultofAjaxWait);
                    if (resultofAjaxWait) {
                        break;
                    }

                    Thread.sleep(1000);
                }
            } else {
                Reporter.log("ERROR : Web driver: " + driver
                        + " cannot execute javascript");
            }
        } catch (InterruptedException e) {
            Reporter.log("ERROR : Interrupted Exception " + e.getMessage());
        }
    }

    public static String getBrowserversion(WebDriver driver)
    {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName();
        String browserVersion = cap.getVersion();
        String browser = browserName + " " + browserVersion;
        return browser;
    }

    /**
     * This method is to click on given element and wait for page to load
     * @param link: locator of any element
     */
    public static void clickAndWait(String link) {
        if(!isElementPresent(link)){
            waitForElementPresent(link);
        }
        click(link);
        waitForPageToLoad(timeout);
        waitForAjax(timeoutInt);
    }

    private static void click(String obj){
        System.out.println("<<Click>>" + obj);
        try {
            //waitForElementPresent(obj);
            WebElement query1= locator(obj);

            query1.click();

        }catch(Exception e)
        {
            System.out.println("not able to click on Object"+obj + e.getMessage());
            fail("not able to click on Object"+obj );
        }
    }

    public static WebElement locator(String locObj) {
        WebElement query = null;
        By by;
        by=locatorBy(locObj);
        try
        {
            try{
                if (!driver.findElements(by).isEmpty()) {
                    query=driver.findElement(by);
                }
                if (query==null) {
                    by=By.name(locObj);
                    if (!driver.findElements(by).isEmpty()) {
                        query=driver.findElement(by);
                    }
                }
            }
            catch(NoSuchElementException te)
            {
                query=null;
                System.out.println("*************catch1"+query);
                if (query==null)
                {
                    by=By.name(locObj);
                    if (!driver.findElements(by).isEmpty()){
                        query=driver.findElement(by);
                    }
                }
            }
        }
        catch(TimeoutException te){
            query=null;
            System.out.println("*************catch2"+query);
            return query;
        }
        System.out.println("*************" + query);
        return query;
    }

    public static void waitAndType(String searchBoxLocator, String optionName) {
        WebElement element = driver.findElement(locatorBy(searchBoxLocator));
        if(element.isDisplayed()) element.sendKeys(optionName);
    }

    public static boolean isElementPresent(String locator) {
        return driver.findElement(By.xpath(locator)).isDisplayed();
    }

    public static void switchToFrame(int index){
        driver.switchTo().defaultContent();
        driver.switchTo().frame(index);
    }

    public static void switchToFrame(String locator){
        driver.switchTo().defaultContent();
        driver.switchTo().frame(locator);
    }

    public static void popUpSwitch(String locator) throws InterruptedException{
        ArrayList<String> currentWindowList = new ArrayList<String>(driver.getWindowHandles());
        waitForElementPresent(locator);
        click(locator);

        while (true) {
            Thread.sleep(4000);
            ArrayList<String> newWindowList = new ArrayList<String>(driver.getWindowHandles());
            if (newWindowList.size() > currentWindowList.size()) {
                driver.switchTo().window(newWindowList.get(newWindowList.size() - 1));
                driver.manage().window().maximize();
                currentWindowList = newWindowList;
            } else break;
        }
    }

    public static void waitForElementPresent(String element) {
        System.out.println("waitForElementPresent>>" + element);
        try{
            WebDriverWait wait=new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locatorBy(element)));
        }
        catch(TimeoutException te){
            System.out.println("*************");
        }
    }

    public static By locatorBy(String locObj) {
        By by=null;
        try
        {
            if(locObj.startsWith("//"))
            {
                by=By.xpath(locObj);
            }
            else if(locObj.startsWith("link"))
            {
                String[] locObj1=locObj.split("link=");
                System.out.println(locObj1[1]);
                by=By.linkText(locObj1[1]);
            }
            else if(locObj.startsWith("css="))
            {
                String[] locObj1=locObj.split("css=");
                System.out.println(locObj1[1]);
                by=By.cssSelector(locObj1[1]);
            }
            else if(locObj.startsWith("id="))
            {
                String[] locObj1=locObj.split("id=");
                System.out.println(locObj1[1]);
                by=By.id(locObj1[1]);

            }
            else if(locObj.startsWith("name="))
            {
                String[] locObj1=locObj.split("name=");
                System.out.println(locObj1[1]);
                by=By.name(locObj1[1]);
            }
            else if(locObj.startsWith("label="))
            {
                String[] locObj1=locObj.split("label=");
                System.out.println(locObj1[1]);
                by=By.name(locObj1[1]);
            }
            else
            {
                by=By.id(locObj);
            }
        }
        catch(NoSuchElementException e){}

        return by;
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
