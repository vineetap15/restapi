Feature: to validate user creation and updation

    to create a new user, update an existing user and validate the successfull creation and updation

    @createPet
    Scenario Outline: Create a User
        Given the user has POST api of create user
        When the user enters the data "<name>" and "<job>"
        When the user hits the POST api
        Then the user should be created

        Examples:
            | name | job      |
            | vine11 | engineer |
            | vine22 | doctor |
            | vine33 | manager |
            | vine44 | lead |