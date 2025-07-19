CREATE TABLE login_site (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    nascimento DATE,
    cargo VARCHAR(100),
    empresa VARCHAR(100),
    cadastro TIMESTAMP
);

