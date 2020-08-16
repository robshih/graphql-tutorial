package io.robshih.graphqltutorial.cucumber.context;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ComponentScan("io.robshih.graphqltutorial.cucumber")
@ContextConfiguration(classes = {CucumberSpringContextConfig.class})
public class CucumberSpringContextConfig {

}
