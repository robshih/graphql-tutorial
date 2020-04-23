package io.robshih.graphqltutorial.controller;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import graphql.ExecutionInput;
import graphql.GraphQL;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @PostMapping
    public Map<String, Object> post(@RequestHeader(value = HttpHeaders.CONTENT_TYPE, required = false) String contentType,
                                    @RequestParam(value = "query", required = false) String query,
                                    @RequestParam(value = "operationName", required = false) String operationName,
                                    @RequestParam(value = "variables", required = false) String variablesJson,
                                    @RequestBody(required = false) String body) {
        // TODO RS: add support for multiple operations in a single query
        Gson gson = new Gson();
        if (MediaType.APPLICATION_JSON
                .toString()
                .equals(contentType)) {
            GraphRequest graphRequest = gson.fromJson(body, GraphRequest.class);
            return executeQuery(graphRequest.getQuery());
        } else if (MEDIA_TYPE_APPLICATION_GRAPHQL.equals(contentType)) {
            return executeQuery(body);
        } else if (!StringUtils.isEmpty(query)) {
            return executeQuery(query);
        } else {
            // TODO RS: implement proper error handling
            Map<String, Object> errorResponse = Maps.newHashMap();
            errorResponse.put("data", "");
            errorResponse.put("error", "Error on PUT request");

            return errorResponse;
        }
    }

    private Map<String, Object> executeQuery(String query) {
        return graphQl
                .execute((ExecutionInput
                        .newExecutionInput(query)
                        .build()))
                .toSpecification();
    }

    @Data
    private class GraphRequest {
        private final String query;
    }
}
