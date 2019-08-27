Feature: to validate user details

    to validate a user details


    @getUser
    Scenario: get a user details
        Given the user has GET api of user
        When the user hits the GET api with valid id "5"
        Then the user should be validated