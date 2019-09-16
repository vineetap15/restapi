Feature: Get all the repositories of a user

Fetch all the repos beongs to a user

@getallrepos
Scenario: 
        Given User has a GET Api which will lists all repos for a user "/users/vineetap15/repos"
        When User hits the GET Api to get all repos
        Then all the repositories should gets fetched