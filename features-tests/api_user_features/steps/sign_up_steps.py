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

@given('user with password {password} and email {emailp} at {emaild} without name')
def step_impl(context, password, emailp, emaild):
    context.user = {
        'password': password,
        'email': (emailp + '@' + emaild)
    }

@given('user with password {password} and email {emailp} at {emaild} with empty name')
def step_impl(context, password, emailp, emaild):
    context.user = {
        'password': password,
        'email': (emailp + '@' + emaild),
        'name': ''
    }

@given('user {username} with email {emailp} at {emaild} without password')
def step_impl(context, username, emailp, emaild):
    context.user = {
        'username': username,
        'email': (emailp + '@' + emaild)
    }

@given('user {username} with email {emailp} at {emaild} with empty password')
def step_impl(context, username, emailp, emaild):
    context.user = {
        'username': username,
        'email': (emailp + '@' + emaild),
        'password': ''
    }

@given('user {username} with password {password}')
def step_impl(context, username, password):
    context.user = {
        'username': username,
        'password': password
    }

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

#
# WHENs
#

@when('signing up')
def step_impl(context):
	endpoint = '/api/user'
	context.execute_steps(u'''
		When we POST it at {} as JSON
	'''.format(endpoint))

#
# THENs
#

@then('registration should succeed')
def step_impl(context):
	context.execute_steps(u'''
		Then HTTP 201 should be returned
	''')

@then('registration should fail due to conflict')
def step_impl(context):
	context.execute_steps(u'''
	    Then HTTP 409 should be returned
	    And response should be coded as application/json with charset UTF-8
	    And response should have message field
	''')
