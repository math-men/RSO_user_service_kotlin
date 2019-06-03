from behave import fixture, use_fixture
import psycopg2

conn_string = "host='localhost' user='sshort' password='sshort'"

@fixture
def posgresql_connection(context):
    print "Connecting to user service database..."
    context.dbconn = psycopg2.connect(conn_string)
    yield context.dbconn
    context.dbconn.close()

def before_all(context):
    use_fixture(posgresql_connection, context)

def before_scenario(context, scenario):
	# Delete all records from USERS table
    context.dbconn.cursor().execute("DELETE FROM USERS")
    context.dbconn.commit()
