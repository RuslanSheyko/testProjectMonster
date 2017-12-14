package Driver;

import TestMethods.AdditionalMethods;
import TestMethods.WriteLog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static Driver.InitialDrivers.driver;


public abstract class EventHandler implements WebDriverEventListener {

  public static String exception;
  @Override
  public void beforeAlertAccept(WebDriver webDriver) {

  }

  @Override
  public void afterAlertAccept(WebDriver webDriver) {

  }

  @Override
  public void afterAlertDismiss(WebDriver webDriver) {

  }

  @Override
  public void beforeAlertDismiss(WebDriver webDriver) {

  }

  @Override
  public void beforeNavigateRefresh(WebDriver webDriver) {

  }

  @Override
  public void afterNavigateRefresh(WebDriver webDriver) {

  }

  @Override
  public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

  }

  @Override
  public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

  }
  public void beforeClickOn(WebElement arg0, WebDriver arg1) {
    new AdditionalMethods().waitForLoad();

  }

  public void afterClickOn(WebElement arg0, WebDriver arg1) {
  }

  public void afterNavigateForward(WebDriver arg0) {
    new AdditionalMethods().waitForLoad();
  }

  public void afterNavigateTo(String arg0, WebDriver arg1) {
    new AdditionalMethods().waitForLoad();
  }

  public void beforeNavigateTo(String arg0, WebDriver arg1) {
    new AdditionalMethods().waitForLoad();
  }


  public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2)
  {
  }

  public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
  }

  /***
   * NOT In USE
   *
   */
  public void afterScript(String arg0, WebDriver arg1) {
  }

  public void afterNavigateBack(WebDriver arg0) {
  }

  public void beforeNavigateBack(WebDriver arg0) {
    System.out.println("Navigate Back ");
  }

  public void beforeNavigateForward(WebDriver arg0) {

    System.out.println("Navigate Forward ");
    new AdditionalMethods().waitForLoad();
  }

  public void beforeScript(String arg0, WebDriver arg1) {
  }

  public void onException(Throwable arg0, WebDriver arg1) {
    screenBrowser();
  }

  private void screenBrowser() {
    WriteLog writeLog = new WriteLog();
    String path = System.getProperty("user.dir");
    String newPath = path + "\\..\\Screenshot\\" + InitialDrivers.sessionID;
    writeLog.createDirectory(newPath);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh_mm_ss");
    Calendar now = Calendar.getInstance();
    Screenshot screenshot = new AShot().shootingStrategy(new ViewportPastingStrategy(1000)).takeScreenshot(driver);
    try {
      ImageIO.write(screenshot.getImage(), "PNG", new File(newPath + "\\" +
              formatter.format(now.getTime()) + ".png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}