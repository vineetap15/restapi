Feature: to validate editing in a comments respective to an issue

Fetch a issue and edit any one of its comment


@getcommentss
Scenario: User fetch the comments respective to an issue
        Given the user has GET api to read all the comments to an issue
        When the user hits the GET api to read all the comments to an issue
        Then user can view all the comments

@deletecommentss
Scenario: User deletes a comments
        Given the user has a DELETE api to delete a comment
        When user hit the DELETE api for comment deletion
        Then user is able to delete the comment successfully