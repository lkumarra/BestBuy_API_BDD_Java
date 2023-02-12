@Categories
Feature: Get Categories Via ID
  In Order to get products
  I want to told to verify get categories via Id
  API :- GET /categories/{id}

  @GetCategories @PositiveScenario
  Scenario: Get category via Id
    Given I am a valid user
    When I get category with id a 'DVD_Games'
    Then Response should be returned with status code 'OK'
    And Verify the category from Db

  @GetCategories @NegativeScenario
  Scenario: Try to get categories via Invalid product id.
    Given I am a valid user
    When I try get categories with product 'Invalid'
    Then Response should not be returned with status code 'NOTFOUND', name as 'NotFound' and message as "No record found for id 'aaaaaaaaaaaa'"
