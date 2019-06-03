from behave import given, when, then, step

import requests

@given('signed up user {username} with password {password} and email {emailp} at {emaild}')
def step_impl(context, username, password, emailp, emaild):
	endpoint = '/api/user'
	context.execute_steps(u'''
		Given user {} with password {} and email {} at {}
		When signing up
		Then registration should succeed
	'''.format(username, password, emailp, emaild))


@given('signed up user {username} with password {password}')
def step_impl(context, username, password):
	endpoint = '/api/user'
	context.execute_steps(u'''
		Given user {} with password {}
		When signing up
		Then registration should succeed
	'''.format(username, password))

@when('signing up')
def step_impl(context):
	endpoint = '/api/user'
	context.execute_steps(u'''
		When we POST it at {} as JSON
	'''.format(endpoint))

@then('registration should succeed')
def step_impl(context):
	context.execute_steps(u'''
		Then HTTP 201 should be returned
	''')

@then('registration should fail')
def step_impl(context):
	context.execute_steps(u'''
	    Then HTTP 409 should be returned
	    And response should be coded as application/json with charset UTF-8
	    And response should have message field
	''')
