package Driver;

import TestMethods.WriteLog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLException;

import static Driver.InitialDrivers.driver;


public abstract class Drivers {
  /*
     * navigate to new Page by Url
     *
     * @param url page to navigate
     */
  public static void navigateUrl(String url) throws SQLException, ClassNotFoundException, InterruptedException {
    driver.navigate().to(url);
  }


  /**
   * wait Until element will be Clickabel
   *
   * @param selector = web element on site
   * @param log=     you text to send it on log
   * @return WebElement
   */
  protected static WebElement waitUntilElementClickable(By selector, String log) {
    WriteLog.TestLog(log);
    return new WebDriverWait(driver, Driver.InitialDrivers.WaitTime).until(ExpectedConditions.elementToBeClickable(selector));
  }
  protected static String getText(By selector) {

    return new WebDriverWait(driver, InitialDrivers.WaitTime).until(ExpectedConditions.presenceOfElementLocated(selector)).getText();
  }


}
