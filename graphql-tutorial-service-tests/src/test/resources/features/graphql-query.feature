Feature: Resolve graphql queries
  Return results of a graphql query via various HTTP requests

  Scenario: GET graphql query
    Given I have data to retrieve
    When I make a GET request
    Then I should receive a success response