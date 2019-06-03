from behave import given, when, then, step

import requests

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

#
# WHENs
#

@when('we POST it at {endpoint} as JSON')
def step_impl(context, endpoint):
    url = (context.server_url) + endpoint
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

@then('HTTP {status_code:d} should be returned')
def step_impl(context, status_code):
    print "Status code: " + str(context.response.status_code)
    assert context.response.status_code == status_code

@then('response should be coded as {mimetype} with charset {charset}')
def step_impl(context, mimetype, charset):
    print context.response.headers
    content_type = context.response.headers['Content-Type']
    [got_mimetype, got_charset] = content_type.split(';')
    assert got_mimetype == mimetype
    assert got_charset.split('=')[1] == charset

@then('response should have {fieldname} field')
def step_impl(context, fieldname):
    print context.response.json()
    assert fieldname in context.response.json()

@then('response should not have {fieldname} field')
def step_impl(context, fieldname):
    print context.response.json()
    assert fieldname not in context.response.json()

@then('server error should be returned')
def step_impl(context):
    context.execute_steps(u'''
        Then HTTP 500 should be returned
        And response should be coded as application/json with charset UTF-8
        And response should have message field
    ''')
