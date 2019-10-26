Feature: Edit a repository

Validate a user should be able to edit an existing repository

@editrepo
Scenario Outline: 
        Given User has a PATCH Api to edit a repository "/repos/vineetap15/HelloWorld"
        When user enter new details "<reponame>", "<desc>","<homepage>","<private>","<has_issues>","<has_projects>" and "<has_wiki>"
        When User hits the PATCH Api to edit a repository
        Then the repo should gets updated with new details

        Examples:
        |reponame|desc                    |homepage          |private|has_issues|has_projects|has_wiki|
        |Mytestrepo|this is new test repo|https://github.com| true  |true      |true        |true    |