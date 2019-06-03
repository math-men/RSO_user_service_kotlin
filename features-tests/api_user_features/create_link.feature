Feature: Creating links

User (either logged in or not) may create shortened links from provided URLs. These shortened links may be used then to redirect users to specified URLs.

Depending on user type, there are two types of generated links: standard and premium. Below are some details about them:
 - Standard links - created by non logged in users:
    -- These links can be used only for redirecting.
    -- No analytics can be performed with them.
    -- They exists with limited period on server (specified by TTL parameter - time to leave)
 - Premium links - created by logged in Premium users:
    -- These links have unlimited existence on the server
    -- Analytics are performed on them (e.g. storing number of hits)

Both types of links share a common format: they are build from letters A-Z.

  Scenario: Not logged users can create standard links
    Given not logged in user
    When creating link to https://github.com
    Then both original and shortened link should be returned

  Scenario:
