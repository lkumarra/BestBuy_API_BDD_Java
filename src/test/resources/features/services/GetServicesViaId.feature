@Services
Feature: GetServices
	In Order to get services
	I want to told to verify services
	API :- GET /services/{id}

@GetProducts @PostiveScenarios
Scenario: Get All Services
	Given I am a valid user
	When I get 'Geek_Squad_Services' service
	Then Response should be returned with status code 'OK'
	And Verify the service from Db
	
@GetProducts @NegativeScenario
Scenario: Get All Services
	Given I am a valid user
	When I get 'Invalid' service
	Then Response should not be returned with status code 'NOTFOUND', name as 'NotFound' and message as "No record found for id '0'"
	
@GetProducts @NegativeScenario
Scenario: Get All Services
	Given I am a valid user
	When I get 'NotExisting' service
	Then Response should not be returned with status code 'NOTFOUND', name as 'NotFound' and message as "No record found for id '9999'"