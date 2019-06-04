Feature: Find all user links

After login user can retrieve a list of currently active links. Having a list of links he can use one of them later to check e.g. hits counter. Of course, when user has not generated any link yet, retrieval should result in empty list.

  Scenario: User can retrieve list of active links
    Given some logged in user
    And created link to https://github.com
    When retrieving list of active links
    Then link to https://github.com should be returned
