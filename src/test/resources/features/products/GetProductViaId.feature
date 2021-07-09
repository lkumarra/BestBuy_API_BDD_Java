@Products
Feature: Get Product via Id
  In Order to get products via Id
  I want to told to verify products
  API :- GET /products/{id}

  @GetProducts @PositiveScenario
  Scenario: Get product via Id
    Given I am a valid user
    When I get product with product 'DuracellCopperTechBattery'
    Then Response should be returned with status code 'OK'
    And Verify the product from Db

  @GetProducts @NegativeScenario
  Scenario: Try to get product via Invalid product id.
    Given I am a valid user
    When I try get product with product 'Invalid'
    Then Response should not be returned with status code 'NOTFOUND', name as 'NotFound' and message as "No record found for id '0'"
