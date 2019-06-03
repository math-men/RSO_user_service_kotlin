from behave import given, when, then, step

import requests

@given('user {username} with changed password from {old_password} to {new_password}')
def step_impl(context, username, old_password, new_password):
    context.execute_steps(u'''
        Given logged in user {} with password {}
        When changing password to {}
        Then password should be changed
    '''.format(username, old_password, new_password))

@when('changing password to {new_password}')
def step_impl(context, new_password):
    context.new_password = new_password

    context.url = context.user_service_url + '/api/user/password'
    context.payload = {
        'oldPassword': context.user['password'],
        'newPassword': new_password
    }

    context.response = requests.post(url=context.url, json=context.payload, headers=context.headers)

@when('logging in with old password')
def step_impl(context):
    context.execute_steps(u'''
        When logging in with password {}
    '''.format(context.old_password))

@when('logging in with new password')
def step_impl(context):
    context.execute_steps(u'''
        When logging in with password {}
    '''.format(context.new_password))

@then('password should be changed')
def step_impl(context):
    context.execute_steps(u'''
        Then HTTP 200 should be returned
    ''')

    context.old_password = context.user['password']
    context.user['password'] = context.new_password
