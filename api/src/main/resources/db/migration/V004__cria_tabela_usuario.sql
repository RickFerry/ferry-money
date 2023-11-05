CREATE TABLE usuario (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   nome VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   senha VARCHAR(255) NOT NULL,
   CONSTRAINT pk_usuario PRIMARY KEY (id)
);

ALTER TABLE usuario ADD CONSTRAINT uc_usuario_email UNIQUE (email);