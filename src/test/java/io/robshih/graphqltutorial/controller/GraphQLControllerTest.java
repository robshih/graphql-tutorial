package io.robshih.graphqltutorial.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import graphql.GraphQL;
import io.robshih.graphqltutorial.annotation.UnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GraphQLControllerTest {

    @Mock
    private GraphQL mockGraphQL;
    private GraphQLController graphQLController;

    @BeforeEach
    void setup() {
        graphQLController = new GraphQLController(mockGraphQL);
    }

    @DisplayName("Simple POST test")
    @UnitTest
    void simpleUnitTest() {
        assertTrue(true);
    }

}
