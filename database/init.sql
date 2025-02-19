DO
$$
BEGIN
   IF NOT EXISTS (
      SELECT
      FROM pg_catalog.pg_database
      WHERE datname = 'mydatabase'
   ) THEN
      CREATE DATABASE mydatabase;
END IF;
END
$$;

CREATE ROLE postgres WITH LOGIN SUPERUSER PASSWORD 'devpassword';
GRANT ALL PRIVILEGES ON DATABASE mydatabase TO postgres;

\c mydatabase;

CREATE TABLE IF NOT EXISTS Users (
                                     id SERIAL PRIMARY KEY,
                                     email VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL
    );

INSERT INTO Users (email, first_name, last_name) VALUES
                                                     ('alice@example.com', 'Alice', 'Smith'),
                                                     ('bob@example.com', 'Bob', 'Johnson'),
                                                     ('carol@example.com', 'Carol', 'Williams');
