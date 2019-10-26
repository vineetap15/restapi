Feature: Deetel a repository

Validate a user should be able to delete an existing repository

@deleterepo
Scenario: 
        Given User has a DELETE Api to delete a repository "/repos/vineetap15/MyHelloWorld"
        When User hits the DELETE Api to delete a repository
        Then the repo should gets deleted