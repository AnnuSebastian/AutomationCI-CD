
@tag
Feature: ErrorValidations
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When I Logged in with username <name> and password <password>
    Then "Incorrect email or password" message is dipalyed.

        Examples: 
      | name                    | password         |
      | rahulshetty@gmail.com   |    IamKing@000   | 
