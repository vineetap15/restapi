Feature: to validate user details

    to validate a user details


    @getUserById
    Scenario: get a user details
        Given the user has GET api of user
        When the user hits the GET api with valid id "6"
        Then the user should be fetched