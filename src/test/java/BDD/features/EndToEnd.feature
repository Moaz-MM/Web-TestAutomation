Feature: Checkout functionality
  In order to do online shopping
  As a customer
  I want to login, choose product, add it to cart and complete checkout

  Scenario: End-to-End scenario

    Given I am in the home page
    When I log in to the website
    And I choose product and add product to cart
    And Choose product options
    Then I should be able to complete checkout
    And place order