package Driver;


import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;

class DriversCapabilities {
    DesiredCapabilities chromeCapabilities() {
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("myhttpproxy:3337");
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        capability.setCapability("chrome.switches", Arrays.asList("--incognito"));
        capability.setBrowserName("chrome");
        capability.setPlatform(Platform.ANY);
        capability.setCapability("proxy", proxy);
        return capability;
    }

    DesiredCapabilities firefoxCapabilities() {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(true);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        capability.setCapability(FirefoxDriver.PROFILE, profile);
        return capability;
    }
}
