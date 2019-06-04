from behave import given, when, then, step

import requests

#
# GIVENs
#

#
# WHENs
#

@when('retrieving list of active links')
def step_impl(context):
	context.execute_steps(u'''
		Given empty request
        And user service endpoint equal to /api/link
        And request method GET
        When sending request
	''')

#
# THENs
#

@then('link to {url} should be returned')
def step_impl(context, url):
	context.execute_steps(u'''
        Then HTTP 200 should be returned
        And response should be coded as application/json with charset UTF-8
        And response should be a list
        And response should have size 1
	''')
	link = context.response_json[0]
	assert link['url'] == url
	assert link['shortenedUrl'] == context.shortened_url
