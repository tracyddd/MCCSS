package utils;

import org.openqa.selenium.WebDriver;

public class Utilities {
    private final TestContextSetup testContextSetup;
    private final WebDriver driver;

    public Utilities(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.driver = testContextSetup.driver;  // initiate driver to testContextSetup.driver which can simply use "driver" instead of "testContextSetup.driver"
    }

   }

