
Feature: Error Validation
  I want to use this template for my feature file
  
  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce page
    When Logged in with username  <name> and password <password>
    Then "Incorrect email or password" message is dispalyed
   
   Examples: 
      | name                  | password  |
      | mpavithra18@gmail.com | Test12098 |