CREATE TABLE users (
    username VARCHAR(20) PRIMARY KEY,
    password VARCHAR(255)
);

INSERT INTO users (username, password)
VALUES ('test', '1234');

SELECT 'Initialization script ran successfully';