package stepDefinitionsKart;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import utils.Log;
import utils.TestContextSetup;

import java.util.Iterator;
import java.util.Set;

public class GreenKartOfferPage {
    private final TestContextSetup testContextSetup;
    private final WebDriver driver;
    private final String productName;
    public static String offerPageProductName;

    // constructor-  TestContextSetup initial driver
    public GreenKartOfferPage(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.driver = testContextSetup.driver; // initial driver to testContextSetup.driver which can simply use "driver" instead of "testContextSetup.driver"
        this.productName =testContextSetup.ProductName;
    }

    @Then("User searched for shortname {string} in offers page")
    public void user_searched_for_shortname_in_offers_page(String shortName) throws InterruptedException {
        //offer page->enter->grab text
        driver.findElement(By.linkText("Top Deals")).click();
        Reporter.log("Top deals hyperlink is clicked");
        Set<String> s1=driver.getWindowHandles();
        Iterator<String> i1=s1.iterator();
        String parentWindows=i1.next();
        String childWindow=i1.next();
        driver.switchTo().window(childWindow);
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortName);
        Reporter.log("Input shortName: "+shortName);
        Thread.sleep(3000);
        offerPageProductName=driver.findElement(By.cssSelector("tr td:nth-child(1)")).getText();
        Reporter.log("Reporter.log is showing here");
        assert true;
    }

    @And("Validate product name in offers page matches with Landing page")
    public void validate_product_name_in_offers_page_matches_with_landing_page() {
        Reporter.log("offerPageProductName is: "+offerPageProductName);
        Reporter.log(productName);
        Log.info("Log: Validate product name in offers page matches with Landing page");
       Assert.assertEquals(offerPageProductName, productName);
    }
}
