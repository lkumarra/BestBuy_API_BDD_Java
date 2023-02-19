@Stores
Feature: GetStores
  In Order to get stores
	I want to told to verify stores
	API :- GET /stores

  @GetStores @PositiveScenarios
  Scenario: Get all stores
    Given I am a valid user
    When I get all stores
    Then Response should be returned with status code 'OK'
    And Verify the stores list from Db
