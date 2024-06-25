@tag
Feature: Purchase product on Ecommerce Website
 

 Background:
 Given user is launched on E-commerce WebPage

  @Regression
  Scenario Outline: user is able to purchase product using E-commerce Website
    Given user enters <username> and <password> in E-commerce Homescreen WebPage
    When user add the <productName> into cart
    And user validates <productName> in checkoutpage and proceeds to payment page
    Then user "THANKYOU FOR THE ORDER." veries the text on confirmation page

    Examples: 
      | username  						| password 					| productName 	 |
      |"tushar1234@gmail.com	"	|		"Tushar1234	"		|"ZARA COAT 3" |
      
