package tech.indus340.complexa.testing;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "tech.indus340.complexa",
        plugin = {"pretty", "html:build/reports/cucumber.html"}
)
public class CucumberTest {

}