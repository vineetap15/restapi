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
            | vine | engineer |

    @getUser
    Scenario: get a user details
        Given the user has GET api of user
        When the user hits the GET api with valid id
        Then the user should be validated


    @updateUser
    Scenario Outline: update a user details
        Given the user has PUT api of a user with valid id
        When user enters the updated data "<newName>" and "<newJob>"
        When the user hits the PUT api
        Then the user should be updated

        Examples:
            | newName  | newJob |
            | vineetag | doctor |

    @deleteUser
    Scenario: delete a user
        Given the user has DELETE api with valid id
        When the user hits the DELETE api
        Then the user should gets deleted


