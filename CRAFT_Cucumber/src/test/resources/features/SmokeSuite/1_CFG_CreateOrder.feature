Feature: CFG-Create order in cfg
 @createordercfg @sox @smoke
Scenario: CFG- Create order in cfg
Given User is in the CFG login page of the application
When login to CFG using the valid userid and password and click on login button
Then Click on Order Processing
And Click on Call Entry
And Click on Start Button
And Enter "Automated Tester" Caller Name
And Select location name "US_Automated" and click on search
And Select city,Building,Floor,Site from the dropdown
And Click on Problem
And Type "Automated Testing" in problem code and select one
And Enter "Test work order for release verification. Please disregard" in the Problem Description
And Click on Create Order and Verify workorder created and strore the order id
And Click on End Button and stop the process
And Close the details pop up window
