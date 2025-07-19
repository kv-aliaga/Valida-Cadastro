CREATE TABLE login_site (
    id serial PRIMARY KEY,
    nome varchar(100) NOT NULL,
    email varchar(150) UNIQUE NOT NULL,
    nascimento date CHECK (EXTRACT (YEAR FROM AGE(nascimento)) BETWEEN 18 AND 100),
    cargo varchar(50) NOT NULL,
    empresa varchar(100) NOT NULL,
    cadastro timestamp DEFAULT CURRENT_TIMESTAMP
);

select * from login_site