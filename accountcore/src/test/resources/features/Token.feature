Feature: Token
  Scenario: Generate token
    Given I have a token service
    When I call generate token service
    Then The result should not be empty

  Scenario: Validate token
    Given I have a token service prepared
    When I call validate token service
    Then The result should be valid