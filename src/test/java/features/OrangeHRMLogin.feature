@LoginFeature @Regression
Feature: OrangeHRM Login Functionality
@Scenario1 @Smoke
Scenario: User should be able to login with valid credentials
Given User is on the OrangeHRM login page
When  User enters username as "Admin"
When  User enters password as "admin123" 
When  User clicks on the submit button
#Then  User should land on the Dashboard page