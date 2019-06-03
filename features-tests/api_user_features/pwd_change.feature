Feature: Password change

Logged in users could change their passwords. The final password (at the moment) has no requirements according to its format

  Scenario: User can change its password
    Given logged in user Adam with password Okay
    When changing password to MyNewOkay
    Then password should be changed

  Scenario: User can not log in with previous password
    Given user Adam with changed password from Okay to MyNewOkay
    When logging in with old password
    Then log in should fail as unauthorized

  Scenario: User can log in with new password
    Given user Adam with changed password from Okay to MyNewOkay
    When logging in with new password
    Then log in should succeed

  # Scenario: User can not use previous token after password change
  #   Given user Adam with changed password from Okay to MyNewOkay
  #   When accessing some protected endpoint using old token
  #   Then it should fail as unauthorized

