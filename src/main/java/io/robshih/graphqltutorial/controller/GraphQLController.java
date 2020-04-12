package io.robshih.graphqltutorial.controller;

import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/graphql")
class GraphQLController {

    @Autowired
    private GraphQL graphQl;

    @GetMapping
    public String get(@RequestBody String query) {
        return graphQl.execute(query).toString();
    }
}
