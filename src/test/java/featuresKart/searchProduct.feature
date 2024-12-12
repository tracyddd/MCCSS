Feature: GreenKart Testing

  @smoke
  Scenario Outline: Search product in Home/Landing Page to validate if it matches in ProductName Column from feature file
    Given User is on GreenKart landing page
    When User searched with shortname <Name> and extracted actual name of product
    Then Validate product name in landing page matches with Product Name <ExpectedProductName> Column
  Examples:
    |Name  | ExpectedProductName |
    |"App"| "Apple"     |

  @smoke
  Scenario Outline: Search product in both Landing and Offers page to validate the outcome
    Given User is on GreenKart landing page
    When User searched with shortname <Name> and extracted actual name of product
    Then User searched for shortname <Name> in offers page
    And Validate product name in offers page matches with Landing page
    Examples:
      |Name  |
      |"Tom"|
      |"Ban"|
      |"Beet"|

 # @regression @smoke
 #  Scenario: Open the GreenKart webpage, and I can find broccoli and apple carrot listed on the page.

 # @sanity
 #  Scenario: After opening GreenKart webpage, I can see "top deals" with hyperlink is displayed.

 # @regression @sanity
 #  Scenario: Clicking on the "+" button increases the quantity.

 #  Scenario: Clicking on "Add to Cart" adds the vegetable to the shopping bag.

 #  Scenario: I can click the "x" button to remove the vegetable from the shopping bag.