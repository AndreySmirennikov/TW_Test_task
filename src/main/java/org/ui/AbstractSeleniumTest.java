package org.ui;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract test class for e2e tests.
 */
public abstract class AbstractSeleniumTest {

    public static String LOCAL = "en";

    /** Logger. */
    protected static final Logger log = LoggerFactory.getLogger(AbstractSeleniumTest.class);

    /** Default await timeout. */
    public static final Duration DFLT_AWAIT_TIMEOUT = Duration.ofSeconds(20);

    /** Extension version. */
    public static final String EXTENSION_VERSION = "1.7.0_0";

    @SneakyThrows @BeforeEach
    protected void setUp(TestInfo testInfo) {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy= "eager";
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File(this.getClass().getClassLoader().getResource(EXTENSION_VERSION+".crx").toURI()));
        System.setProperty("chromeoptions.prefs","intl.accept_languages="+LOCAL);

        Configuration.browserCapabilities = new DesiredCapabilities();
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

        Selenide.open();
        WebDriver driver = WebDriverRunner.getWebDriver();

        Selenide.Wait()
            .withTimeout(DFLT_AWAIT_TIMEOUT)
            .withMessage("Trust Wallet onboarding tab did not opened")
            .until(condition -> driver.getWindowHandles().size()==2);
        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(1));
    }

    @AfterEach
    protected void cleanUp(TestInfo testInfo) {
        Selenide.closeWebDriver();
    }
}
