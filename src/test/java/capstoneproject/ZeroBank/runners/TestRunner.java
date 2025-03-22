package capstoneproject.ZeroBank.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "capstoneproject.ZeroBank.stepdefinitions",
    plugin = {"pretty", "json:target/cucumber-reports-json/Cucumber.json","html:target/cucumber-reports.html"} // Generate JSON report
    //plugin = {"pretty", "html:target/cucumber-reports"}
)
public class TestRunner extends AbstractTestNGCucumberTests 
{
	
} 