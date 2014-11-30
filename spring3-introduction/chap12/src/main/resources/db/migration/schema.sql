DROP TABLE IF EXISTS owner;
DROP TABLE IF EXISTS pet;

CREATE TABLE owner (
    owner_id IDENTITY PRIMARY KEY,
    owner_name VARCHAR(255)
);

CREATE TABLE pet (
    pet_id IDENTITY,
    pet_name VARCHAR(255),
    owner_name VARCHAR(255),
    price INTEGER,
    birth_date DATE
);
