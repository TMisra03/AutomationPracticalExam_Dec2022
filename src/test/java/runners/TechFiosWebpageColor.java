package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src\\test\\resources\\features\\TechFiosWebpageColor.feature",
//		features = "classpath:features", 
		glue = "steps", 
		tags = {"@colorScenario1" }, 
		monochrome = true, 
		dryRun = false, 
		plugin = { "pretty",
					"html:target/reports/cucumber.html", 
					"json:target/reports/cucumber.json" 
					}
		)
public class TechFiosWebpageColor {
	
}
