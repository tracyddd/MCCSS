package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Arrays;

public class TestContextSetup {
    public WebDriver driver;
    public String ProductName;

    public TestContextSetup() {

        ConfigReader configReader = new ConfigReader();
        String browser = configReader.getProperty("browser");  // 从 global.properties 获取浏览器类型

        switch (browser.toLowerCase()) {
            case "chrome":
                // Accept Https Certificates
                ChromeOptions chromeOptions = new ChromeOptions();
                //Block pop=up windows
                chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
                System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\ChromeDriver\\chromedriver.exe");
                driver = new ChromeDriver(chromeOptions); // Initialize driver here to add "options" in () for accepting certificates
                /* setup Proxy
                //Proxy proxy=new Proxy();
                //proxy.setHttpProxy("username","password");
                //options.setCapability("proxy", proxy);  */
               break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\GeckoDriver\\geckodriver.exe");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                System.setProperty("webdriver.edge.driver", "C:\\Drivers\\EdgeDriver\\msedgedriver.exe");
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();



    }
}
