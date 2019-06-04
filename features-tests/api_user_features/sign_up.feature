Feature: Sign up

Everyone can sign up to the user service using /api/user.
There are some requirements, like:
 - user name must be unique,
 - both username and password must be provided
 - email is optional.
 - (at the moment) there are no validation rules for password

  Scenario: User can register to create new account
    Given user Adam with password Okay and email Adam at Okay.pl
    When signing up
    Then registration should succeed

  Scenario: User can not register account with same name
    Given signed up user Adam with password Okay and email Adam at Okay.pl
    And user Adam with password Okay and email Adam at Okay.pl
    When signing up
    Then registration should fail due to conflict

  Scenario: User can register account with same password and email
    Given signed up user Adam with password Okay and email Adam at Okay.pl
    And user Edward with password Okay and email Adam at Okay.pl
    When signing up
    Then registration should succeed

  Scenario: User must provide name during registration
    Given user with password Okay and email Adam at Okay.pl without name
    When signing up
    Then server error should be returned

  # Scenario: User must provide non-empty name during registration
  #   Given user with password Okay and email Adam at Okay.pl with empty name
  #   When signing up
  #   Then server error should be returned

  Scenario: User must provide password during registration
    Given user Adam with email Adam at Okay.pl without password
    When signing up
    Then server error should be returned

  # Scenario: User must provide non-empty password during registration
  #   Given user Adam with email Adam at Okay.pl with empty password
  #   When signing up
  #   Then server error should be returned
