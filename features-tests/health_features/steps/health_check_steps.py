from behave import given, when, then, step

import requests

@when('we GET at /health')
def step_impl(context):
    url = 'http://localhost:8080/health'
    context.response = requests.get(url)

@then('HTTP OK should be returned')
def step_impl(context):
    assert context.response.ok
