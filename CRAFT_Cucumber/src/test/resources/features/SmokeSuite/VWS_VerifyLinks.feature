Feature: Verify Available Links are Working for the User in VWS application
@cfgverifylink @sox @smoke @createordercfg
Scenario: Verify Available Links are Working for the User in VWS application
Given User Navigate to VWS application
When User Login to VWS Application with valid credentials
Then Verify that the link Home loads if available
And Verify that the link Open Orders Today loads if available
And Verify that the link Open Orders loads if available
And Verify that the link Closed Orders loads if available
And Verify that the link Vendor Profile loads if available
And Verify that the link Service Center Lookup loads if available
And Verify that the link Import and Export loads if available
And Verify that the link Complete Work Order loads if available
And User Logout From the VWS application successfully

