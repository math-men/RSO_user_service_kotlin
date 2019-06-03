from behave import given, when, then, step

import requests
import re

#
# GIVENs
#

@given('user {username} with password {password} and email {emailp} at {emaild}')
def step_impl(context, username, password, emailp, emaild):
    context.user = {
        'username': username,
        'password': password,
        'email': (emailp + '@' + emaild)
    }

@given('user with password {password} and email {emailp} at {emaild}')
def step_impl(context, password, emailp, emaild):
    context.user = {
        'password': password,
        'email': (emailp + '@' + emaild)
    }

@given('user {username} with email {emailp} at {emaild}')
def step_impl(context, username, emailp, emaild):
    context.user = {
        'username': username,
        'email': (emailp + '@' + emaild)
    }

@given('user {username} with password {password}')
def step_impl(context, username, password):
    context.user = {
        'username': username,
        'password': password
    }

@given('empty JSON request')
def step_impl(context):
    context.payload = {}
    context.mimetype = 'JSON'

@given('{fieldname} field equal to {value}')
def step_impl(context, fieldname, value):
    assert 'payload' in context
    context.payload[fieldname] = value

@given('not logged in user')
def step_impl(context):
    if 'token' in context:
        del context.token
    if 'headers' in context:
        del context.headers

@given('url equal to {url}')
def step_impl(context, url):
    context.url = url

@given('user service endpoint equal to {endpoint}')
def step_impl(context, endpoint):
    url = (context.user_service_url + endpoint)
    context.execute_steps(u'''
        Given url equal to {}
    '''.format(url))

@given('request method {method}')
def step_impl(context, method):
    assert (method == 'POST') or (method == 'GET')
    context.method = method

#
# WHENs
#

@when('sending request')
def step_impl(context):
    assert context.mimetype is 'JSON'
    if context.method == 'POST':
        context.response = requests.post(url=context.url,
                                         json=context.payload)
    elif context.method == 'GET':
        context.response = requests.get(url=context.url,
                                        json=context.payload)
    else:
        assert False

@when('we POST it at {endpoint} as JSON')
def step_impl(context, endpoint):
    url = (context.user_service_url) + endpoint
    context.request = {
        'url': url,
        'payload': context.user
    }

    request = context.request
    context.response = requests.post(url=request['url'], json=request['payload'])

@when('we repeat the same request')
def step_impl(context):
    context.prev_response = context.response

    request = context.request
    context.next_response = requests.post(url=request['url'], json=request['payload'])

#
# THENs
#

@then('{fieldname} field should have only letters A-Z')
def step_impl(context, fieldname):
    context.execute_steps(u'''
        And {} field should match regex ^[A-Z]+$
    '''.format(fieldname))

@then('HTTP {status_code:d} should be returned')
def step_impl(context, status_code):
    assert context.response.status_code == status_code

@then('response should be coded as {mimetype} with charset {charset}')
def step_impl(context, mimetype, charset):
    content_type = context.response.headers['Content-Type']
    [got_mimetype, got_charset] = content_type.split(';')
    assert got_mimetype == mimetype
    assert got_charset.split('=')[1] == charset

    if mimetype is "application/json":
        context.response_json = context.response.json()

@then('response should have {fieldname} field')
def step_impl(context, fieldname):
    assert fieldname in context.response.json()

@then('response should not have {fieldname} field')
def step_impl(context, fieldname):
    assert fieldname not in context.response.json()

@then('{fieldname} field should be equal to {value}')
def step_impl(context, fieldname, value):
    assert fieldname in context.response_json
    assert context.response_json[fieldname] == value

@then('{fieldname} field should be not empty')
def step_impl(context, fieldname):
    assert fieldname in context.response_json
    assert context.response_json[fieldname] is not ''

@then('{fieldname} field should match regex {regex}')
def step_impl(context, fieldname, regex):
    assert fieldname in context.response_json
    assert re.match(regex, context.response_json[fieldname])

@then('server error should be returned')
def step_impl(context):
    context.execute_steps(u'''
        Then HTTP 500 should be returned
        And response should be coded as application/json with charset UTF-8
        And response should have message field
    ''')

@then('it should fail as unauthorized')
def step_impl(context):
    context.execute_steps(u'''
        Then HTTP 401 should be returned
        And response should be coded as application/json with charset ISO-8859-1
        And response should have message field
    ''')
