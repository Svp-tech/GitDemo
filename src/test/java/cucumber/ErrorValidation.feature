
@tag
Feature: Error Validation
  I want to use this template for my feature file

  

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Eccomerce Page
    When Logged in with UserName <name> and the Password <password>
    Then "Incorrect email or password." this message displayed

   Examples: 
      | name  								| password 					|
      | shlok23@gmail.com 		| Shlok&13 				  |
