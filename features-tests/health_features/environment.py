from behave import fixture, use_fixture
import os

def before_all(context):
	context.server_url = os.environ['SERVER_URL']
