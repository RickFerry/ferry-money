CREATE TABLE pessoa (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   nome VARCHAR(255) NOT NULL,
   ativo BOOLEAN NOT NULL,
   logradouro VARCHAR(255),
   numero VARCHAR(255),
   complemento VARCHAR(255),
   bairro VARCHAR(255),
   cep VARCHAR(255),
   cidade VARCHAR(255),
   estado VARCHAR(255),
   CONSTRAINT pk_pessoa PRIMARY KEY (id)
);