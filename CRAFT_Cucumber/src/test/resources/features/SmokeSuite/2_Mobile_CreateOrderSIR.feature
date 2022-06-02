Feature: Creation of a Service request in SIReqAndroid application
@mobilesir  
Scenario: Create a Service request in SIReqAndroid application
Given The SIReqAndroid application is launched
When The user enters the Customer id in settings and selects the Language as English and clicks Save
And The user enters valid credentials and logs in to the application
Then the user opens the hamburger menu if the Request Service option is available click the same or else close the hamburger and click the Quick request link 
And verifies if the location levels are automatically filled up
And if the user has navigated using Request Service option then fill details for Problem code or else ignore
And enter the Problem description
And click on the Request Service button to create the Work Order
And verify that the Work Order has been created and store the Work Order for Cancellation