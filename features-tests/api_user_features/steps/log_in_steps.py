from behave import given, when, then, step

import requests

#
# GIVENs
#

@given('logged in user {username} with password {password}')
def step_impl(context, username, password):
    context.execute_steps(u'''
        Given signed up user {} with password {}
        When logging in
        Then log in should succeed
    '''.format(username, password))

@given('some logged in user')
def step_impl(context):
	context.execute_steps(u'''
		Given logged in user Adam with password Okay
	''')

#
# WHENs
#

@when('logging in')
def step_impl(context):
	endpoint = '/api/user/token'
	context.execute_steps(u'''
		When we POST it at {} as JSON
	'''.format(endpoint))

@when('logging in with password {password}')
def step_impl(context, password):
	context.user['password'] = password
	context.execute_steps(u'''
		When logging in
	''')

@when('logging in as {username} with password {password}')
def step_impl(context, username, password):
	context.execute_steps(u'''
		Given user {} with password {}
		When logging in
	'''.format(username, password))

@when('logging in again')
def step_impl(context):
	# TODO add checks if request is same as in log in
	context.prev_token = context.token
	context.prev_headers = context.headers
	context.execute_steps(u'''
		When we repeat the same request
	''')

#
# THENs
#

@then('log in should succeed')
def step_impl(context):
	context.execute_steps(u'''
	    Then HTTP 200 should be returned
	    And response should be coded as application/json with charset ISO-8859-1
	    And response should have token field
	''')
	context.token = context.response.json()['token']
	context.headers = { 'Authorization': 'Bearer ' + context.token}

@then('both tokens should be equal')
def step_impl(context):
	assert context.token == context.prev_token

@then('log in should fail as unauthorized')
def step_impl(context):
	context.execute_steps(u'''
		Then it should fail as unauthorized
	    And response should not have token field
	''')
