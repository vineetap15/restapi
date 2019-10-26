Feature: to create issues with multiple comments and validate those comments

Create a issue with multile comments and vlidate all the comments details

@createissue
Scenario Outline: create an issue
        Given the user has POST api of create issue "vineetap15/restapi/issues"
        When the user enters the title "<title>"
        When the user enters the  body "<body>"
        When the user enters the assignees
        |vineetap15|
        When the user enters the labels
        |bug1|
        |bug2|
        When the user hits the POST api for issue creation
        Then the issue should be created

        Examples:
        |title       | body                          | milestone  |
        |Found a bug |I'm having a problem with this.|1           |


@entercomments
Scenario Outline: enter comments
        Given the user has POST api of create a comments "/vineetap15/restapi/issues/" and "/comments"
        When the user enter the comment body "<comment>"
        When the user hits the POSt api for comments creation
        Then the comment should be created

        Examples:
        |comment                                |
        |please fix this bug                    |
        |please make the necessary changes      |

@editcomments
Scenario: User edits a comments
        Given the user has a PATCH api to edit a comment "/vineetap15/restapi/issues/comments/"
        When user edited the comment "updated comment"
        When user hit the PATCH api
        Then user is able to edit the comment successfully


@getcomments
Scenario: User fetch the comments respective to an issue
        Given the user has GET api to read all the comments to an issue "vineetap15/restapi/issues/" and "/comments"
        When the user hits the GET api to read all the comments to an issue
        Then user can view all the comments


@deletecomments
Scenario: User deletes a comments
        Given the user has a DELETE api to delete a comment "/vineetap15/restapi/issues/comments/"
        When user hit the DELETE api for comment deletion
        Then user is able to delete the comment successfully
