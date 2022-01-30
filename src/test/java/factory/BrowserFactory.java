package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserFactory {

    private static WebDriver driver;

    public static WebDriver createBrowserInstance(String browser) throws IllegalAccessException, MalformedURLException {

        switch (Browser.valueOf(browser.toUpperCase())) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--ignore-certificate-errors");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--window-size=1440,900");
                driver = new ChromeDriver(chromeOptions);
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
                FirefoxOptions options = new FirefoxOptions();
                FirefoxProfile profile = new FirefoxProfile();
                profile.setAcceptUntrustedCertificates(true);
                profile.setAssumeUntrustedCertificateIssuer(false);
                options.setLogLevel(FirefoxDriverLogLevel.FATAL);
                options.setCapability("marionette", true);
                options.addPreference("dom.webnotifications.enabled", false);
                driver = new FirefoxDriver(options);
                break;
            case REMOTE:
                System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
                ChromeOptions chromeOptions_remote = new ChromeOptions();
                chromeOptions_remote.addArguments("--headless");
                chromeOptions_remote.addArguments("--ignore-certificate-errors");
                chromeOptions_remote.addArguments("--disable-popup-blocking");
                chromeOptions_remote.addArguments("--disable-notifications");
                URL url = new URL("http://localhost:4444");
                driver = new RemoteWebDriver(url, chromeOptions_remote);
                break;
            default:
                throw new IllegalAccessException("Browser '" + browser + "' is invalid");
        }
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        BrowserFactory.driver = driver;
    }
}
