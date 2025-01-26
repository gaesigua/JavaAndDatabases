@OtherLoginFeature @Regression
Feature: OrangeHRM Other Login Functionality

Background:
Given User is on the OrangeHRM login page

@Scenario1OfOtherLogin
Scenario: User should not be able to login with invalid credentials
When  User enters username as "Admin1"
When  User enters password as "admin123" 
And  User clicks on the submit button
Then  User should not land on the Dashboard page

@Scenario2OfOtherLogin @Smoke
Scenario: User should not be able to login with invalid credentials
When  User enters username as "Admin"
When  User enters password as "admin1234" 
And  User clicks on the submit button
Then  User should not land on the Dashboard page

@Scenario3OfOtherLogin @Smoke
Scenario: User should not be able to login with invalid credentials
When  User enters username as "Admin1"
When  User enters password as "admin1234" 
And  User clicks on the submit button
Then  User should not land on the Dashboard page