package io.robshih.graphqltutorial.controller;

import graphql.ExecutionInput;
import graphql.GraphQL;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/graphql")
class GraphQLController {

    private static final String MEDIA_TYPE_APPLICATION_GRAPHQL = "application/graphql";

    @Autowired
    private GraphQL graphQl;

    @GetMapping
    public Map<String, Object> get(@RequestParam("query") String query) {
        return executeQuery(query);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> post(@RequestBody(required = false) GraphRequest graphRequestBody) {
        // TODO RS: add support for multiple operations in a single query
        // TODO RS: implement error handling
        return executeQuery(graphRequestBody.getQuery());
    }

    @PostMapping
    public Map<String, Object> postQuery(@RequestParam(value = "query", required = true) String query) {
        return executeQuery(query);
    }

    @PostMapping(consumes = MEDIA_TYPE_APPLICATION_GRAPHQL)
    public Map<String, Object> postMediaTypeGraphQl(@RequestBody(required = true) String body) {
        return executeQuery(body);
    }

    private Map<String, Object> executeQuery(String query) {
        return graphQl
                .execute((ExecutionInput
                        .newExecutionInput(query)
                        .build()))
                .toSpecification();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class GraphRequest {
        private String query;
    }
}
