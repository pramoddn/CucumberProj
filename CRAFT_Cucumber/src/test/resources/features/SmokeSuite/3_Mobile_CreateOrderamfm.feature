Feature: Create a Service request in aMFM application
@mobileamfm
Scenario: Create a Service request in aMFM application
Given Launch the aMFM application
Then Enter the Customer id in settings and Select the Language as English and click on save
And Enter valid credentials and login to the application
And Click on the hamburger menu and click on Create Work Order for Dispatch
And Select the Assignment Type as "Employee"
And Select "Automated Tester" for the field Assignment
And Enter "%%%" in the Property ID field 
And Now select the property from the dropdown preferably select a data that data for Floor and Room
And Enter the text "Automated" in Problem code text field and then click on the dropdown below set and select the value
And Enter "Test work order for release verification. Please disregard" the problem description
And Enter the Estimated Service Cost as "0.00"
And Select "SelfGen" from order type if available
And Click on Submit and Store the WO

