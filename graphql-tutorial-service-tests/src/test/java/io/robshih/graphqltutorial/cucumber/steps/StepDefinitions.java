package io.robshih.graphqltutorial.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.robshih.graphqltutorial.cucumber.context.ScenarioContext;
import org.springframework.beans.factory.annotation.Autowired;

public class StepDefinitions {

    private static final String GRAPH_ENDPOINT = "/graphql";

    @Autowired
    private ScenarioContext scenarioContext;

    @Given("I have data to retrieve")
    public void iHaveDataToRetrieve() {

    }

    @When("I make a {word} request")
    public void iMakeARequest(String requestType) {
        scenarioContext.setResponse(RestAssured.get(GRAPH_ENDPOINT));
    }

    @Then("I should receive a success response")
    public void iShouldReceiveResultsForTheQuery() {
    // assertEquals(200, scenarioContext.getResponse().getStatusCode());
    }
}
