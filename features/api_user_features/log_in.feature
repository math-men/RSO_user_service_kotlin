Feature: Log in

Signed up users could log in to the user service using valid password. It is realized by requesting a token, using /api/user/token, which is valid for a specified amount of time

  Scenario: User can log in to the service
    Given signed up user Adam with password Okay
    When logging in
    Then log in should succeed

  Scenario: User can log in twice using same name
    Given logged in user Adam with password Okay
    When logging in again
    Then log in should succeed
    And both tokens should be equal

  Scenario: User can not log in to non existing account
    Given signed up user Adam with password Okay
    When logging in as Olek with password Okay
    Then log in should fail as unauthorized

  Scenario: User must provide valid password to log in
    Given signed up user Adam with password Okay
    When logging in with password NotOkay
    Then log in should fail as unauthorized

