from behave import fixture, use_fixture
import psycopg2
import os

@fixture
def posgresql_connection(context):
    print "Getting user service DB access variables..."
    host = os.environ['POSTGRES_HOST']
    dbname = os.environ['POSTGRES_DATABASE_NAME']
    user = os.environ['POSTGRES_USERNAME']
    password = os.environ['POSTGRES_PASSWORD']

    print "Connecting to user service DB..."
    conn_string = "host='{}' dbname='{}' user='{}' password='{}'".format(host, dbname, user, password)
    context.dbconn = psycopg2.connect(conn_string)

    yield context.dbconn
    context.dbconn.close()

def before_all(context):
    context.user_service_url = os.environ['USER_SERVICE_URL']
    use_fixture(posgresql_connection, context)

def before_scenario(context, scenario):
	# Delete all records from USERS table
    context.dbconn.cursor().execute("DELETE FROM USERS")
    context.dbconn.commit()
