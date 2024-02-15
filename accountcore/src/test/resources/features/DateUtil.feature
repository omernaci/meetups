Feature: Date Utility Functions
  As a developer
  I want to ensure that the DateUtil class behaves as expected
  So that I can use its date-related utility functions with confidence

  Scenario: Checking if a date is a weekend
    Given a date "2024-03-02"
    When I check if it is a weekend
    Then the result should be true

  Scenario: Calculating the next work date
    Given a date "2022-10-01"
    When I calculate the next work date
    Then the result should be "2022-10-03"