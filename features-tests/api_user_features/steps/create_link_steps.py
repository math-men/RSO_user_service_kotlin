from behave import given, when, then, step

import requests

#
# GIVENs
#

@given('link pointing to {url}')
def step_impl(context, url):
    context.execute_steps(u'''
        Given empty JSON request
        And url field equal to {}
    '''.format(url))

#
# WHENs
#

@when('creating link to {url}')
def step_impl(context, url):
    context.execute_steps(u'''
        Given link pointing to {}
        And user service endpoint equal to /api/link
        And request method POST
        When sending request
    '''.format(url))

#
# THENs
#

@then('link should be created')
def step_impl(context):
    context.execute_steps(u'''
        Then HTTP 200 should be returned
    '''.format(context.url))

@then('both original and shortened link should be returned')
def step_impl(context):
    context.execute_steps(u'''
        Then link should be created
        And response should be coded as application/json with charset UTF-8
        And response should have url field
        And url field value should be equal to {}
        And response should have shortenedUrl field
        And shortenedUrl field should be not empty
        And shortenedUrl field should have only letters A-Z
    ''')
