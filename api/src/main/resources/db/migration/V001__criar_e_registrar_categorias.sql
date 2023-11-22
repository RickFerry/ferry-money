CREATE TABLE categoria (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(255) NOT NULL,
   CONSTRAINT pk_categoria PRIMARY KEY (id)
);

ALTER TABLE categoria ADD CONSTRAINT uc_categoria_nome UNIQUE (nome);

INSERT INTO categoria (nome) values ('Lazer');
INSERT INTO categoria (nome) values ('Alimentação');
INSERT INTO categoria (nome) values ('Supermercado');
INSERT INTO categoria (nome) values ('Farmácia');
INSERT INTO categoria (nome) values ('Outros');