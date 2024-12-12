package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features ={"src/test/java/featuresKart"},
        tags="@smoke",  // tags="not @Regression"
        glue= {"stepDefinitionsKart"},
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber-kart.html",
                "json:target/cucumber-kart.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
}
)
public class TestNGRunnerKart extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel=false)
    public Object[][] scenarios(){
        return super.scenarios();
    }
}



