Feature:Store creation

    Scenario: create an store
        Given configure Store controll
        When we given store to create
            | name     |  descriptioin  |  phone   |
            | royalTreat | veg&nonVeg     | 1001      |
        Then find all and check for
            | name     |  descriptioin  |  phone   |
            | royalTreat | veg&nonVeg     | 1001      |
