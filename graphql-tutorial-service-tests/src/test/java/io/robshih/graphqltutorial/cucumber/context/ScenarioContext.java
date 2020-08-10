package io.robshih.graphqltutorial.cucumber.context;

import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@ScenarioScope
public class ScenarioContext {

    private Response response;
}
