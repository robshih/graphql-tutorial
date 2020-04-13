package io.robshih.graphqltutorial.controller;

import graphql.ExecutionInput;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/graphql")
class GraphQLController {

    @Autowired
    private GraphQL graphQl;

    @GetMapping
    public Object get(@RequestParam("query") String query) {
        return graphQl
                .execute(ExecutionInput
                        .newExecutionInput(query)
                        .build())
                .toSpecification();
    }
}
