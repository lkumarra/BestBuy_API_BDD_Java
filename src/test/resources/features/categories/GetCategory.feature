@Categories
Feature: GetCategories
  In Order to get products
  I want to told to verify products
  API :- GET /categories

  @GetCategories
  Scenario: Get All Categories
    Given I am a valid user
    When I get all categories
    Then Response should be returned with status code 200
    And Verify the categories list from Db
