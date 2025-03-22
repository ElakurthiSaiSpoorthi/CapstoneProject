Feature: Add a New Payee Functionality

  Scenario: Successfully Adding a New Payee
    Given User logs in and navigates to Add New Payee page
    When User enters payee name "Sai Spoorthi"
    And User enters address "Karmanghat, Hyderabad"
    And User enters account number "9874561230"
    And User clicks add payee button
    Then User should see "Payee added successfully" message

  Scenario: Add Payee with Missing Details
    Given User logs in and navigates to Add New Payee page
    When User enters payee name "John"
    And User enters address "29 sri nagar, Dindi"
    And User leaves account number empty
    And User clicks add payee button
    Then Error message "All fields are required." will be displayed
