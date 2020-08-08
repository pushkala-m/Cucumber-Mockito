Feature: As a customer i wish to store multiple addresses and set one of them as default
    Scenario: creating store address and set one as default
        Given store creation with address
            | addressType   |   houseNoOrBuildingName  | landmark | roadNameAreaOrStreet  | city  | state | pincode |
            | address1      | AAAA                     | AAAA     | AAAA                  | AAAA  | AAAA  | AAAA    |

        And store creation with address
            | addressType   |   houseNoOrBuildingName  | landmark | roadNameAreaOrStreet  | city  | state | pincode |
            | address2      | BBBBB                   | BBBBB    | BBBBB                | BBBBB  | BBBBB  | BBBBB   |
        When  if the selected addressType is 'address1'
        Then  the default addressType should  'address1'

