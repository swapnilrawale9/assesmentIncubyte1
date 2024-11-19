package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class) // This is mandatory for Cucumber tests to run with JUnit.
@CucumberOptions(
        features = "src/test/resources/features", // Path to the feature files.
        glue = "steps",                          // Path to the step definitions.
        plugin = {"pretty", "html:target/cucumber-reports"}, // Output format.
        monochrome = true                        // Makes console output readable.
)
public class TestRunner {
}
