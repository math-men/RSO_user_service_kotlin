from behave import given, when, then, step

import requests

#
# GIVENs
#

@given('created link to {url}')
def step_impl(context, url):
    context.execute_steps(u'''
        When creating link to {}
        Then link should be created
    '''.format(url))

@given('create link request')
def step_impl(context):
    context.execute_steps(u'''
        Given JSON request
        And user service endpoint equal to /api/link
        And request method POST
    ''')

#
# WHENs
#

@when('creating link to {url}')
def step_impl(context, url):
    context.execute_steps(u'''
        Given create link request
        And url field equal to {}
        When sending request
    '''.format(url))

@when('creating link without providing any url')
def step_impl(context):
    context.execute_steps(u'''
        Given create link request
        When sending request
    ''')

@when('creating link with empty url field')
def step_impl(context):
    context.execute_steps(u'''
        Given create link request
        And empty field url
        When sending request
    ''')

@when('creating link again to the same url')
def step_impl(context):
    context.execute_steps(u'''
        When creating link to {}
    '''.format(context.payload['url']))

#
# THENs
#

@then('link should be created')
def step_impl(context):
    context.execute_steps(u'''
        Then HTTP 200 should be returned
        And response should be coded as application/json with charset UTF-8
        And response should have url field
        And url field should be equal to {}
        And response should have shortenedUrl field
        And shortenedUrl field should be not empty
        And shortenedUrl field should have only letters A-Z
    '''.format(context.payload['url']))

    if 'shortened_url' in context:
        context.old_shortened_url = context.shortened_url

    context.shortened_url = context.response_json['shortenedUrl']

@then('link should not be created')
def step_impl(context):
    context.execute_steps(u'''
        Then server error should be returned
    ''')

@then('both shortenings should be different')
def step_impl(context):
    assert context.shortened_url != context.old_shortened_url
