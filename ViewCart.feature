Feature: Login to e-comm , add items to wishlist and move cheapest item to  cart
	Scenario: Account Login
    Given Customer opens URL
    And Customer Land on Home page
    When Mouse Hover sign in and clicks on sign in
    When Enters credential to Login
    | 9600082828 | Pk2021!! |
     

  Scenario: View My wishlist
    Given Customer refresh the page
    When search for a product and add to wishist
     | product   | 
     | TCL     | 
     | Samsung  | 
     | Nokia    | 
     | Motorola   | 
    Then view  my wishlist
    And find four selected items in my wishlist
     
  Scenario: View My cart
    Given Customer refresh the page
    And view  my wishlist
    And find lowest price item and add to my cart
    Then verify the item in my cart
    And view  my wishlist
    And Clear Wishlist
    And Sign out of the account
  