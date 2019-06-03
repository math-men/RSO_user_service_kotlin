Feature: Health check

Everyone can check health of user service to determine,
whether it is running or not

  Scenario: User can check health of service
    When we GET at /health
    Then HTTP OK should be returned
