Feature: This is a verification for Http Request (http://localhost:8080/textContent/getUsedTextContentList)
  Scenario: When Called TextContent Data / Verifiy Content Data
    Given Request Occured
    When Get Text ContentData
    Then Verify Text ContentData