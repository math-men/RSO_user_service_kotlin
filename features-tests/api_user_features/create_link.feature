Feature: Creating links

User (either logged in or not) may create shortened links from provided URLs. These shortened links may be used then to redirect users to specified URLs.

Depending on user type, there are two types of generated links: standard and premium. Below are some details about them:
 - Standard links - created by non logged in users:
    -- These links can be used only for redirecting.
    -- No analytics can be performed with them.
    -- They exists with limited period on server (specified by TTL parameter - time to leave)
 - Premium links - created by logged in, Premium users:
    -- These links have unlimited existence on the server
    -- Analytics are performed on them (e.g. storing number of hits)

Both types of links share a common format: they are build from letters A-Z.

  Scenario: Not logged users can create standard links
    Given not logged in user
    When creating link to https://github.com
    Then link should be created

  Scenario: Logged in users can create premium links
    Given some logged in user
    When creating link to https://github.com
    Then link should be created

  Scenario: User can create links with the same url and have different shortenings
    Given some logged in user
    And created link to https://github.com
    When creating link again to the same url
    Then link should be created
    And both shortenings should be different

  Scenario: User can create links with different urls and have differenet shortenings
    Given some logged in user
    And created link to https://github.com
    When creating link to https://newgalaxy.com
    Then link should be created
    And both shortenings should be different

  Scenario: To create a link, user must provide url
    Given some logged in user
    When creating link without providing any url
    Then link should not be created

  Scenario: To create a link, user must provide a non-empty url
    Given some logged in user
    When creating link with empty url field
    Then link should not be created
