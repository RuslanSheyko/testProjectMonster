package TestMethods;

import Driver.InitialDrivers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.Set;

import static Driver.InitialDrivers.driver;


public class AdditionalMethods {


    public int randomGenerate(int maxData, int minData) {
        Random random = new Random();
        return random.nextInt(maxData - minData + 1) + minData;
    }
    /**
     * Wait until page return state 'ready'
     */
    public void waitForLoad() {
        StopWatch watch = new StopWatch();
        watch.start();
        try {
            new WebDriverWait(driver, InitialDrivers.WaitTime).until((ExpectedCondition<Boolean>)
                    wd -> {
                        assert wd != null;
                        return ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete");
                    });
            watch.stop();
        }
       catch (Exception ex)
       {
           System.out.println("waitForLoad: "+ex.getMessage());
       }
    }

    /**Q
     * Wait until all ajax activites is stoped
     */
    public void ajaxWait() {
       String urlName=driver.getCurrentUrl().substring(0,25);
       if( urlName.contains("https://test2.cashup")||urlName.contains("http://10.77.55.24:10000/"))
         return ;
        StopWatch watch= new StopWatch();
        watch.start();
        new WebDriverWait(driver, InitialDrivers.WaitTime).until((ExpectedCondition<Boolean>)
                wd -> ((JavascriptExecutor)wd).executeScript("return jQuery.active").toString().equals("0"));
        watch.stop();
        String message="Wait Time";
        if (watch.getElapsedTime() > 5000) {
              message = "Critical Wait Until Ajax Stop: ";
        }
    }


    class StopWatch {

        private long startTime = 0;
        private long stopTime = 0;
        private boolean running = false;

        void start() {
            this.startTime = System.currentTimeMillis();
            this.running = true;
        }

        void stop() {
            this.stopTime = System.currentTimeMillis();
            this.running = false;
        }


        /**
         * Get time between start way of method to end of method
         *
         * @return long
         */
        long getElapsedTime() {
            long elapsed;
            if (running) {
                elapsed = (System.currentTimeMillis() - startTime);
            } else {
                elapsed = (stopTime - startTime);
            }
            return elapsed;
        }
    }
    public void WaitTime(long time) {
        // System.out.println("Wait Time Thread Sleep");
        try {
            Thread.sleep((time * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Select iframe on a page
     */
    public void selectWindow() {
        WaitTime(100);
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
    }
    /**
     * Select iframe on a page
     */
    public void selectParentWindow() {
        WaitTime(100);
        driver.close();
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
    }

}
