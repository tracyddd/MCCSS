package stepDefinitionsKart;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.LandingPage;
import utils.ConfigReader;
import utils.Log;
import utils.TestContextSetup;

public class GreenKartLandingPage {
    private final TestContextSetup testContextSetup;
    public WebDriver driver;
    public String landingPageProductName;
    public String offerPageProductName;

    // Constructor to inject the driver from TestContextSetup
    public GreenKartLandingPage(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.driver = testContextSetup.driver;  // Use the driver from TestContextSetup
    }

    @Given("User is on GreenKart landing page")
    public void user_is_on_green_kart_landing_page() {
        // No need to initialize the driver here, as it is already provided by TestContextSetup
        // use ConfigReader to read QAUrl
        ConfigReader configReader = new ConfigReader();
        String url = configReader.getProperty("QAUrl");
        driver.get(url);

        // Using JavaScript Executor to fetch the navigation timing to validate the performance
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long loadTime = (Long) js.executeScript(
                "return window.performance.timing.loadEventEnd - window.performance.timing.navigationStart;"
        );
        System.out.println("Page Load Time: " + loadTime + "ms");
        // Assert the load time is within acceptable limits (e.g., 8000ms)
        Assert.assertTrue(loadTime < 8000, "Page load time is too high! More than 8 seconds");

        driver.manage().window().maximize();
    }

    @ When("User searched with shortname {string} and extracted actual name of product")
    public void user_searched_with_shortname_and_extracted_actual_name_of_product(String shortName)throws InterruptedException {
        LandingPage landingPage = new LandingPage(testContextSetup.driver); // Pass the existing driver
        landingPage.searchItem(shortName);
        landingPage.getSearchText();
        Thread.sleep(3000);
        String productName = landingPage.getProductName(); // Store the extracted product name
        testContextSetup.ProductName = productName; // Set it in the TestContextSetup
    }

    @Then("Validate product name in landing page matches with Product Name {string} Column")
    public void validate_product_name_in_landing_page_matches_with_ProductName_Column(String ExpectedProductName) {
        Log.info("Log: Validate product name matches from its column in feature file");

        LandingPage landingPage = new LandingPage(testContextSetup.driver);
        String productName = landingPage.getProductName(); // Store the extracted product name
        testContextSetup.ProductName = productName; // Set it in the TestContextSetup
        Assert.assertEquals(productName, ExpectedProductName);
    }
}
