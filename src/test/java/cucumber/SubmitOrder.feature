@tag
Feature: Purchase the Order from Ecommerce Website
  I want to use this template for my feature file

   Background:
   Given I landed on Eccomerce Page
   

  @Regression
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with UserName <name> and the Password <password>
    When I add product <productName> to Cart
    And Checkout <productName> in <CountryName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  								| password 					| productName	 	| CountryName |
      | shlok23@gmail.com 		| Shlok&123 			  | ZARA COAT 3		| India 			|
