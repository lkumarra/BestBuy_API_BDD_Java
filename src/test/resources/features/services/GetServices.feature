@Services
Feature: Get services
  In Order to get services
  I want to told to verify services
  API :- GET /services

  @GetProducts @PositiveScenarios
  Scenario: Get all services
    Given I am a valid user
    When I get all services
    Then Response should be returned with status code 'OK'
    And Verify the services list from Db