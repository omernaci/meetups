Feature: Closing an account

  Scenario Outline: Closing an account with various conditions
    Given an account with ID <accountId> and balance <balance>
    When the account is closed on "<closedDate>"
    Then the system should return message "<expectedMessage>"

    Examples:
      | accountId | balance | closedDate | expectedMessage                                   |
      | 1         | 0.00    | 2024-02-20 | Account closed successfully                       |
      | 4         | 100.00  | 2024-02-21 | Account balance must be zero to close the account |
      | 7         | 0.00    | 2024-02-18 | Accounts cannot be closed on weekends.            |
