Feature: Account Service

  Scenario: Opening a valid account
    Given a customer with ID "123"
    When an account is opened with currency "USD" and name "Savings"
    Then the account is successfully created

  Scenario: Attempting to open an account for a corporate customer
    Given a customer with ID "456" of type "CORPORATE"
    Then an account creation exception is thrown