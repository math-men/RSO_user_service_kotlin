from behave import given, when, then, step

import requests
import re

#
# GIVENs
#

@given('empty request')
def step_impl(context):
    if 'payload' in context:
        del context.payload

@given('JSON request')
def step_impl(context):
    context.payload = {}

@given('{fieldname} field equal to {value}')
def step_impl(context, fieldname, value):
    assert 'payload' in context
    context.payload[fieldname] = value

@given('empty field {fieldname}')
def step_impl(context, fieldname):
    assert 'payload' in context
    context.payload[fieldname] = ''

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
    if 'headers' in context:
        headers = context.headers
    else:
        headers = {}
    print "Headers: " + str(headers)

    if context.method == 'POST':
        if 'payload' in context:
            context.response = requests.post(url=context.url,
                                             json=context.payload,
                                             headers=headers)
        else:
            context.response = requests.post(url=context.url, headers=headers)

    elif context.method == 'GET':
        if 'payload' in context:
            context.response = requests.get(url=context.url,
                                            json=context.payload,
                                            headers=headers)
        else:
            context.response = requests.get(url=context.url, headers=headers)

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
    assert fieldname in context.response_json
    assert re.match(r'^[A-Z]+$', context.response_json[fieldname])

@then('HTTP {status_code:d} should be returned')
def step_impl(context, status_code):
    print context.response
    assert context.response.status_code == status_code

@then('response should be coded as {mimetype} with charset {charset}')
def step_impl(context, mimetype, charset):
    content_type = context.response.headers['Content-Type']
    [got_mimetype, got_charset] = content_type.split(';')
    assert got_mimetype == mimetype
    assert got_charset.split('=')[1] == charset

    if mimetype in "application/json":
        context.response_json = context.response.json()

@then('response should be a list')
def step_impl(context):
    assert isinstance(context.response_json, list)

@then('response should have size {n}')
def step_impl(context, n):
    assert (len(context.response_json) == int(n))

@then('response should have {fieldname} field')
def step_impl(context, fieldname):
    assert fieldname in context.response.json()

@then('response should only have {fieldname} field')
def step_impl(context, fieldname):
    assert context.response.json().keys() == [fieldname]

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

@then('server error should be returned')
def step_impl(context):
    context.execute_steps(u'''
        Then HTTP 500 should be returned
        And response should be coded as application/json with charset UTF-8
        And response should only have message field
    ''')

@then('it should fail as unauthorized')
def step_impl(context):
    context.execute_steps(u'''
        Then HTTP 401 should be returned
        And response should be coded as application/json with charset ISO-8859-1
        And response should only have message field
    ''')
