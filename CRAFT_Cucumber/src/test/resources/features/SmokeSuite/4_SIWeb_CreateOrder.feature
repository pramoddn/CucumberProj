Feature: Create order in SIRequest by Facility Maintenance Request Form 
@createordersi @sox @smoke
Scenario: Create order in SIRequest by Facility Maintenance Request Form 
Given User is in the SIRequest application page
When Login with the valid credentials
Then Navigate to Facility Maintenance Request Form if it is visible
And Verify all four location levels have populated
And If visible enter valid problem code then enter problem description and create order  
And Confirm that order has successfully created and store the work order number
And Logout from SIRequest application page