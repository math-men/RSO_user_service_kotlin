## Uruchomienie bazy

```
cd db
docker build -t sshort_user_database .
docker run -d -p 5432:5432 --name sshort_user_database sshort_user_database
```