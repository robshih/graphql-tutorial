package io.robshih.graphqltutorial.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import io.robshih.graphqltutorial.annotation.UnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class GraphQLControllerTest {

    @Mock
    private GraphQL mockGraphQL;
    @Mock
    private ExecutionResult mockExecutionResult;

    @Captor
    private ArgumentCaptor<ExecutionInput> executionInputCaptor;

    private GraphQLController graphQLController;
    private Map<String, Object> expectedResultMap;

    @BeforeEach
    void setup() {
        expectedResultMap = new HashMap<>();

        when(mockGraphQL.execute(any(ExecutionInput.class))).thenReturn(mockExecutionResult);
        when(mockExecutionResult.toSpecification()).thenReturn(expectedResultMap);

        graphQLController = new GraphQLController(mockGraphQL);
    }

    @Nested
    @DisplayName("GET unit tests")
    class GetQueries {
        @DisplayName("Execute GET from query string")
        @UnitTest
        void shouldExecuteQueryFromPlainQueryString() {
            String query = "some query string";

            assertEquals(expectedResultMap, graphQLController.get(query));
            verifyGraphQLQueryExecution(query);
        }
    }

    @Nested
    @DisplayName("POST unit tests")
    class PostQueries {

        @DisplayName("Execute graph query using query string from json structure")
        @UnitTest
        void shouldExecuteGraphQueryFromJsonString() {
            String expectedQueryString = "json query body";

            assertEquals(expectedResultMap, graphQLController.postQuery(expectedQueryString));
            verifyGraphQLQueryExecution(expectedQueryString);
        }

        @DisplayName("Execute graph query using query string")
        @UnitTest
        void shouldExecuteGraphQueryFromQueryString() {
            String expectedQueryString = "query string";

            assertEquals(expectedResultMap, graphQLController.postQuery(expectedQueryString));
            verifyGraphQLQueryExecution(expectedQueryString);
        }

        @DisplayName("Execute graph query using body string")
        @UnitTest
        void shouldExecuteGraphQueryFromRequestBody() {
            String expectedQueryString = "body graph query";

            assertEquals(expectedResultMap, graphQLController.postMediaTypeGraphQl(expectedQueryString));
            verifyGraphQLQueryExecution(expectedQueryString);
        }
    }

    private void verifyGraphQLQueryExecution(String expectedQueryString) {
        verify(mockGraphQL).execute(executionInputCaptor.capture());
        ExecutionInput actualExecutionInput = executionInputCaptor.getValue();
        assertEquals(expectedQueryString, actualExecutionInput.getQuery());
    }
}
