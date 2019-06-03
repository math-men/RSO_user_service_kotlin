#!/bin/bash

echo "Checking environment variables..."
if [ -z ${POSTGRES_HOST+x} ]; then echo " POSTGRES_HOST is unset!" && exit -1; else echo " POSTGRES_HOST is set to '$POSTGRES_HOST'"; fi
if [ -z ${POSTGRES_PORT+x} ]; then echo " POSTGRES_PORT is unset!" && exit -1; else echo " POSTGRES_PORT is set to '$POSTGRES_PORT'"; fi
if [ -z ${POSTGRES_DATABASE_NAME+x} ]; then echo " POSTGRES_DATABASE_NAME is unset!" && exit -1; else echo " POSTGRES_DATABASE_NAME is set to '$POSTGRES_DATABASE_NAME'"; fi
if [ -z ${POSTGRES_USERNAME+x} ]; then echo " POSTGRES_USERNAME is unset!" && exit -1; else echo " POSTGRES_USERNAME is set to '$POSTGRES_USERNAME'"; fi
if [ -z ${POSTGRES_PASSWORD+x} ]; then echo " POSTGRES_PASSWORD is unset!" && exit -1; else echo " POSTGRES_PASSWORD is set to '$POSTGRES_PASSWORD'"; fi
if [ -z ${LINK_SERVICE_URL+x} ]; then echo " LINK_SERVICE_URL is unset!" && exit -1; else echo " LINK_SERVICE_URL is set to '$LINK_SERVICE_URL'"; fi
if [ -z ${USER_SERVICE_URL+x} ]; then echo " USER_SERVICE_URL is unset!" && exit -1; else echo " USER_SERVICE_URL is set to '$USER_SERVICE_URL'"; fi

echo ""
echo "Running test suites..."
behave health_features
echo ""

behave api_user_features
echo ""
