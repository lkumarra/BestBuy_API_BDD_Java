@Stores
Feature: GetServices
  In Order to get services
  I want to told to verify services
  API :- GET /stores/{id}

  @Stores @PositiveScenario
  Scenario: Get Stores via valid Id
    Given I am a valid user
    When I get 'MINNETONKA' stores
    Then Response should be returned with status code 'OK'
    And Verify stores data from Db.

  @Stores @NegativeScenario
  Scenario Outline: 
    Given I am a valid user
    When I try to get '<stores>' stores
    Then Response should not be returned with status code '<statusCode>', name as '<name>' and message as "<message>"

    Examples: 
      | stores      | statusCode | name     | message                        |
      | INVALID     | NOTFOUND   | NotFound | No record found for id '0'     |
      | NONEXISTING | NOTFOUND   | NotFound | No record found for id '99999' |
