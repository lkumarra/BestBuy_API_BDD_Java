@Products
Feature: GetProducts
  In Order to get products
  I want to told to verify products
  API :- GET /products

  @GetProducts
  Scenario: Get All Products
    Given I am a valid user
    When I get all products
    Then Products list returned with status code 200
    And Verify the products list from Db
