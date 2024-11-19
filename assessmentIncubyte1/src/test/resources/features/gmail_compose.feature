Feature: Gmail Compose Functionality

  Scenario: Successfully send an email with a subject and body
    Given the user is logged into Gmail
    When the user clicks on "Compose"
    And enters a valid recipient email address
    And enters "Incubyte" in the "Subject" field
    And enters "QA test for Incubyte" in the "Body" field
    And clicks on "Send"
    Then the email should be sent successfully
    And it should appear in the "Sent" folder

  Scenario: Attempt to send an email without a recipient
    Given the user is logged into Gmail
    When the user clicks on "Compose"
    And enters "Incubyte" in the "Subject" field
    And enters "QA test for Incubyte" in the "Body" field
    And clicks on "Send"
    Then a warning message should be displayed
    And the email should not be sent

  Scenario: Prevent sending an email with an invalid recipient email address
    Given the user is logged into Gmail
    When the user clicks on "Compose"
    And enters "invalid-email@com" as the recipient email address
    And enters "Invalid Test" in the "Subject" field
    And enters "Testing invalid email" in the "Body" field
    And clicks on "Send"
    Then an error message should be displayed
    And the email should not be sent
