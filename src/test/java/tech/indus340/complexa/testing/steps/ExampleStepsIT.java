package tech.indus340.complexa.testing.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleStepsIT {

    private String result = "";

    @Given("I have a configured Cucumber test")
    public void givenIHaveAConfiguredCucumberTest() {
        // Setup initial context if necessary
    }

    @When("I run the test")
    public void whenIRunTheTest() {
        result = "Cucumber test run successfully";
    }

    @Then("I should see the result")
    public void thenIShouldSeeTheResult() {
        assertEquals("1Cucumber test run successfully", result);
    }
}
