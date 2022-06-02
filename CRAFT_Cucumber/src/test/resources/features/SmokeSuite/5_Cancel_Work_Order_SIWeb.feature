Feature: Cancel work order in CFG created in SI Web
@createordersi @sox @smoke 
Scenario: Cancel work order in CFG created in SI Web
Given User is in the CFG login page of the application
When login to CFG using the valid userid and password and click on login button
Then Click on Order Processing
Then I click on "View Modify Work Order"
And Enter work order number
And Click on Submit
And Close all pop-ups if any and Click on the Cancel button
And Enter the Cancellation reason
And Click on Cancel Button
And Verify that the work order has been cancelled
And Logout from CFG application
