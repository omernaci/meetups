Feature: Account Service

  Background:
    Given I have an AccountService

  Scenario Outline: Open a new account within yhe account limit
    Given the customer with id <customerId> is a "<customerType>" customer
    And the customer has <existingAccountCount> existing account(s)
    When the customer opens a new account using AccountService
    Then the account should be created successfully if eligible
    But an exception should be thrown if not eligible
    Examples:
      | customerId | customerType | existingAccountCount |
      | 67890      | CORPORATE    | 0                    |
      | 12345      | INDIVIDUAL   | 4                    |
      | 24680      | INDIVIDUAL   | 2                    |