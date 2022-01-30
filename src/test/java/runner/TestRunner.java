package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
				features = "src/test/java/feature",
				glue = {"steps", "factory"},
				tags = "@smoke",
				plugin={"pretty","html:target/cucumber", "json:target/cucumber.json"})

public class TestRunner {
}