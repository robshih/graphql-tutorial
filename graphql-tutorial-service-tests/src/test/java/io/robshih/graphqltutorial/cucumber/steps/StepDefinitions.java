package io.robshih.graphqltutorial.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class StepDefinitions {

    private static final String GRAPH_ENDPOINT = "/graphql";

    @Given("I have data to retrieve")
    public void iHaveDataToRetrieve() {

    }

    @When("I make a {word} request")
    public void iMakeARequest(String requestType) {
        // RestAssured.get(GRAPH_ENDPOINT);
        // TODO: add DI to pass around context in scenarios
    }

    @Then("I should receive a success response")
    public void iShouldReceiveResultsForTheQuery() {
        // TODO: add asserts
    }
}
