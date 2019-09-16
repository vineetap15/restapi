@repo
Feature: Create a new repository

Validate a user should be able to create a new repository

@createrepo
Scenario Outline: 
        Given User has a POST Api to create a repository "/user/repos"
        When user enter the "<reponame>", "<desc>","<homepage>","<private>","<has_issues>","<has_projects>" and "<has_wiki>"
        When User hits the POST Api to create a repository
        Then the repo should gets created

        Examples:
        |reponame|desc             |homepage          |private|has_issues|has_projects|has_wiki|
        |Mytestrepo|this is test repo|https://github.com| false  |true      |true        |true    |
        |MyHelloWorld|this is test repo|https://github.com| false  |true      |true        |true    |