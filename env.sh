#!/bin/sh

export POSTGRES_HOST=localhost
export POSTGRES_PORT=5432
export POSTGRES_DATABASE_NAME=sshort
export POSTGRES_USERNAME=sshort
export POSTGRES_PASSWORD=sshort
export LINK_SERVICE_URL=http://localhost:8000
export SERVER_PORT=8080
export SERVER_HOST=http://localhost
export USER_SERVICE_URL=$SERVER_HOST:$SERVER_PORT

