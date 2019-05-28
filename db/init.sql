GRANT ALL PRIVILEGES ON DATABASE sshort TO sshort;
SET SCHEMA 'public';

CREATE TABLE USERS
(
    ID       integer PRIMARY KEY,
    USERNAME VARCHAR(50) UNIQUE NOT NULL,
    PASSWORD VARCHAR(50)        NOT NULL,
    EMAIL    VARCHAR(300)       NOT NULL
);

--- index nalozyc na username i haslo

INSERT INTO USERS (ID, USERNAME, PASSWORD, EMAIL)
VALUES (1, 'janek123', 'janekpassword', 'janek123@gmail.com'),
       (2, 'robert456', 'robertpassword', 'robert456@o2.pl'),
       (3, 'donatan789', 'donatanpassword', 'donatan789@gmail.com');