[![Build Status](https://travis-ci.com/math-men/RSO_user_service_kotlin.svg?branch=master)](https://travis-ci.com/math-men/RSO_user_service_kotlin)

## Uruchomienie bazy

```
cd db
docker build -t sshort_user_database .
docker run -d -p 5432:5432 --name sshort_user_database sshort_user_database
```