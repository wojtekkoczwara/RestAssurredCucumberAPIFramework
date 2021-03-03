Feature: Validating Place API'

  @AddPlace
  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "POST" http request
    Then The API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "GetPlaceAPI"

    Examples:
      | name | language   | address                 |
      | Anna | English-EN | Wolnych rodnikow 2      |

    @DeletePlace
    Scenario: Verify if Delete Place functionality is working
      Given DeletePlace payload
      When User calls "DeletePlaceAPI" with "POST" http request
      Then The API call is success with status code 200
      And "status" in response body is "OK"


