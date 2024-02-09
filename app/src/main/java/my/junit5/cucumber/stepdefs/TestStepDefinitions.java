package my.junit5.cucumber.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public class TestStepDefinitions {

    private ScenarioState state = new ScenarioState(0);

    @Given("something exists")
    public void somethingExists() {
        state.setI(1);
    }

    @When("something is executed")
    public void somethingIsExecuted() {
        state.setI(2);
    }

    @Then("something is expected")
    public void somethingIsExpected() {
        Assertions.assertThat(state.getI()).isEqualTo(2);
    }
}
