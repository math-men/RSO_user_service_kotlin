from behave import fixture, use_fixture
import os

def before_all(context):
    context.user_service_url = os.environ['USER_SERVICE_URL']
