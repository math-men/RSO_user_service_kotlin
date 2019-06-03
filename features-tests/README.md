# feature-tests

Feature tests was written using `behave` python framework. It supports behavior driven development (BDD).
These tests are working directly with API shipped from user service server, so it is needed to have an active instance of user service and link service and their databases.

During the tests, databases of both user service and link service are manualy modified, in order to have well-defined init state. Without it, it could be difficult to test e.g. user registration procedure, when user could not sign up twice.

Connection with PostgreSQL database is realized using `psycopg2` python library.

## Requirements

First, install python dependencies:
```sh
pip install -r requirements.txt
```

You also have to specify URL of user service server, and URL and credentials of postgreSQL database. You can either export them or just source to the `env.sh` file:

```sh
# export variables directly
export POSTGRES_HOST=localhost
export POSTGRES_PORT=5432
export POSTGRES_DATABASE_NAME=sshort
export POSTGRES_USERNAME=sshort
export POSTGRES_PASSWORD=sshort
export LINK_SERVICE_URL=http://localhost:8000
export USER_SERVICE_URL=http:/localhost:8080

# or source to the environment file
source ../env.sh
```

## Running tests

First, ensure, that link service, user service and their databases are working correctly. Then, to run all tests just type:

```sh
./run-tests.sh
```

You may also run individual tests suites and even individual feature tests, e.g.:

```sh
# Run some test suite
behave health_features

# Run particular feature test
behave api_user_features/sign_up.feature
```


