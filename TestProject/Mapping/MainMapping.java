package Mapping;

import TestMethods.AdditionalMethods;
import org.openqa.selenium.By;
import Driver.Drivers;


public class MainMapping extends Drivers {

    private String mainUrl="https://account.templatemonster.com/auth/#/";
    private final By emailField = By.cssSelector("input[type='email']");
    private final By passwordField= By.cssSelector("input[type='password']");
    private final By continueBtn= By.cssSelector("#id-index-continue-button button");
    private final By facebookBtn= By.cssSelector("#id-general-facebook-button button");
    private final By forgotBtn= By.cssSelector("#id-forgot-password-link a");
    private final By createBtn= By.cssSelector("#id-create-new-account button");
    private final By allertContainer=By.cssSelector(".notification__container span");
    private final By container= By.cssSelector("h3");
    private final By restartPassBtn= By.cssSelector("#id-send-confirmation-link button");
    private final By existEmailTxt= By.cssSelector("span.not-a-link");
    private final By facebookEmail= By.id("email");
    private final By facebookPass= By.id("pass");
    private final By logInBtn= By.cssSelector("input[value='Log In']");
    private final By badLogin= By.cssSelector("#error_box div");
    private void mainMailPass(String email,String pass) throws Exception
    {
        navigateUrl(mainUrl);
        waitUntilElementClickable(emailField,"Fill Email Field").sendKeys(email);
        waitUntilElementClickable(continueBtn,"Click Button Continue").click();
        waitUntilElementClickable(passwordField,"Fill Password Field").sendKeys(pass);
        waitUntilElementClickable(createBtn,"Click Button create Account").click();
    }

    public String existEmail(String email,String pass) throws Exception {

        navigateUrl(mainUrl);
        waitUntilElementClickable(emailField,"Fill Email Field").sendKeys(email);
        waitUntilElementClickable(continueBtn,"Click Button Continue").click();
        return  getText(existEmailTxt);
    }
    public String email(String email,String pass) throws Exception {

        mainMailPass(email,pass);
        new AdditionalMethods().WaitTime(300);
        return  getText(container);
    }
    public String badPass(String email,String pass) throws Exception {

        mainMailPass(email,pass);
        new AdditionalMethods().WaitTime(300);
        System.out.println(getText(allertContainer));
        return  getText(allertContainer);
    }
    public String forgotPass(String email,String pass) throws Exception {
        navigateUrl(mainUrl);
        waitUntilElementClickable(forgotBtn,"Click Button Forgot Password").click();
        waitUntilElementClickable(emailField,"Send Text to email Field").sendKeys(email);
        waitUntilElementClickable(restartPassBtn,"Click Button Restore Password").click();
        new AdditionalMethods().WaitTime(300);
        System.out.println(getText(allertContainer));
        return  getText(allertContainer);
    }
    public String facebookEnter(String email,String pass) throws Exception {
        String result="";
        navigateUrl(mainUrl);
        AdditionalMethods adM= new AdditionalMethods();
        waitUntilElementClickable(facebookBtn,"Click Button Forgot Password").click();
        adM.selectWindow();
        waitUntilElementClickable(facebookEmail,"Send Text to Facebook email Field").sendKeys(email);
        waitUntilElementClickable(facebookPass,"Fill  Facebook Password Field").sendKeys(pass);
        waitUntilElementClickable(logInBtn,"Click Button Log In").click();
        result= getText(badLogin);
        adM.selectParentWindow();
        waitUntilElementClickable(emailField,"Fill Email Field").sendKeys(email);
        return  result;
    }
}
