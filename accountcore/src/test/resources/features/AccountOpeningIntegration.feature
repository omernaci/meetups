Feature: Account Controller Integration Test

  Scenario: Open account successfully
    Given a customer with ID 1 and type INDIVIDUAL
    When an account is opened balance 1000.00, type "SAVINGS", and status "ACTIVE"
    Then the response status code should be 200
    And the response body should contain "Account opened successfully"