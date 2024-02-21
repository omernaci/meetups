#language: tr
# https://cucumber.io/docs/gherkin/languages/
Özellik: Hesap Açılışı

  Senaryo taslağı: Musteri tipi ve bakiyeye gore yeni bir hesap ac
    Diyelim ki Verilen <customerId> numarali musterinin tipi "<customerType>" olsun
    Eğer ki "<currency>" para biriminden, adi "<accountName>", bakiyesi <balance>, tipi "<accountType>", durumu "<accountStatus>" olan hesap acilmak istenirse
    O zaman hesap <success> sekilde olusturulmalidir.

    Örnekler:
      | customerId | customerType | currency | accountName | balance | accountType | accountStatus | success |
      | 1          | INDIVIDUAL   | USD      | Savings A   | 1000.00 | SAVINGS     | ACTIVE        | true    |
      | 2          | CORPORATE    | EUR      | Savings B   | 1000.00 | INVESTMENT  | ACTIVE        | false   |
      | 3          | CORPORATE    | USD      | Savings C   | 1000.00 | SAVINGS     | ACTIVE        | true    |
      | 3          | INDIVIDUAL   | USD      | Savings D   | 1000.00 | CHECKING    | ACTIVE        | true    |
      | 3          | INDIVIDUAL   | USD      | Savings E   | 1000.00 | SAVINGS     | ACTIVE        | true    |
      | 3          | INDIVIDUAL   | USD      | Savings F   | 1000.00 | INVESTMENT  | ACTIVE        | true    |
      | 4          | INDIVIDUAL   | USD      | Savings G   | -100.00 | SAVINGS     | ACTIVE        | false   |



#Feature: Account Opening

#  Background:
#    Given I have an AccountService

#  Scenario Outline: Open a new account within the account limit, customer type and balance
#    Given the customer with id <customerId> is a "<customerType>" customer
#    And the customer has <existingAccountCount> account
#    When an account is opened with currency "<currency>", name "<accountName>", balance <balance>, type "<accountType>", and status "<accountStatus>"
#    Then the account should be created <success>

#    Examples:
#      | customerId | customerType | existingAccountCount | currency | accountName | balance | accountType | accountStatus | success |
#      | 1          | INDIVIDUAL   | 1                    | USD      | Savings A   | 1000.00 | SAVINGS     | ACTIVE        | true    |
#      | 2          | CORPORATE    | 2                    | EUR      | Savings B   | 1000.00 | INVESTMENT  | ACTIVE        | false   |
#      | 3          | INDIVIDUAL   | 3                    | USD      | Savings C   | 1000.00 | SAVINGS     | ACTIVE        | false   |
#      | 3          | INDIVIDUAL   | 2                    | USD      | Savings D   | 1000.00 | CHECKING    | ACTIVE        | true    |
#      | 3          | INDIVIDUAL   | 1                    | USD      | Savings E   | 1000.00 | SAVINGS     | ACTIVE        | true    |
#      | 3          | INDIVIDUAL   | 2                    | USD      | Savings F   | 1000.00 | INVESTMENT  | ACTIVE        | true    |
#      | 4          | INDIVIDUAL   | 1                    | USD      | Savings G   | -100.00 | SAVINGS     | ACTIVE        | false   |