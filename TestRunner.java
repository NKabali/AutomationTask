package automationtask.Testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/java/ViewCart"},
		glue={"StepDefinition"},stepNotifications=true,
		plugin= {"pretty","html:target/cucumber-reports/Cucumber.html",
				"json:target/cucumber-reports/Cucumber.json","junit:target/cucumber-reports/Cucumber.xml",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})

public class TestRunner {
	

}
