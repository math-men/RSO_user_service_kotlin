from behave import given, when, then, step

import requests

@when('we GET at /health')
def step_impl(context):
    url = context.user_service_url + '/health'
    context.response = requests.get(url=url)

@then('HTTP OK should be returned')
def step_impl(context):
    assert context.response.ok
