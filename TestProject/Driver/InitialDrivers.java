package Driver;


import TestMethods.ConfigRead;
import TestMethods.SetUpFixture;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;


public class InitialDrivers {

    public static final int WaitTime = 30;

    public static WebDriver driver = null;
    public static String sessionID;

    public WebDriver selectDriver() throws Exception{

        Drivers driverName = Drivers.valueOf(ConfigRead.readBaseConfig("Driver"));
        String path = System.getProperty("user.dir");
        String newPath = path + "\\Resources\\Drivers";
        SetUpFixture startSession = new SetUpFixture();
        sessionID = startSession.StartSession();
        if (sessionID.length() < 8)
            startSession.StartSession();
        switch (driverName) {
            case Chrome: {
                DriversCapabilities chrome = new DriversCapabilities();
                System.setProperty("webdriver.chrome.driver", newPath + "\\chromedriver.exe");
                driver = new ChromeDriver(chrome.chromeCapabilities());

                break;
            }
            case Firefox: {
                System.setProperty("webdriver.gecko.driver", newPath + "\\geckodriver.exe");
                DriversCapabilities firefox = new DriversCapabilities();
                driver = new FirefoxDriver(firefox.firefoxCapabilities());

                break;
            }
        }
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
        EventHandler handler = new EventHandler() {
        };
        driver.manage().window().maximize();
        driver = eventDriver.register(handler);
        return driver;
    }

    private enum Drivers {Chrome, Firefox}
}