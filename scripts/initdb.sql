DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    cpf VARCHAR(255),
    email VARCHAR(255)
);
