Feature: Login to the Vendor Activity Web Site 
@VAWSLogin
Scenario: Login to the Vendor Activity Web Site with valid credentials
Given browser open
When User enters valid credentials
And User verifies Home
Then Verify the tabs present in the application

