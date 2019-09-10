Feature: Get a particular repository of a user

Validate a particular reprepository details beongs to a user

@getarepo
Scenario Outline: 
        Given: User has a GET Api which will fetch a repository detail "/repos/vineetap15/restapi"
        When: User hits the GET Api to fetch a repo details
        Then: the repo details should be fetched and verify "<name>" and "<login>" and "<admin_per>" and "<push_per>" and "<pull_per>"

        Examples:
        |name|login|admin_per|push_per|pull_per|
        |restapi|vineetap15|true|true|true|
