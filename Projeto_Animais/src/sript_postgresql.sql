CREATE DATABASE animais_db;

\c animais_db

CREATE TABLE habitats (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TABLE animais (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    especie VARCHAR(100) NOT NULL,
    habitat_id INT,
    FOREIGN KEY (habitat_id) REFERENCES habitats (id) ON DELETE SET NULL
);
