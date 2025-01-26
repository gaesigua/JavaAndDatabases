@LoginwithDatabaseFeature @Regression
Feature: OrangeHRM Login Functionality

@LoginWithDatabaseScenario1 @Smoke
Scenario: User should be able to login with valid credentials
Given User is on the OrangeHRM login page
When  User enters "username" from database
When  User enters "password" from database 
When  User clicks on the submit button
Then  User should land on the Dashboard page