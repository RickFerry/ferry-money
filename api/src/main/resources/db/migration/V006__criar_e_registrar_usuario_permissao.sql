CREATE TABLE usuario_permissao (
  permissao_id BIGINT NOT NULL,
   usuario_id BIGINT NOT NULL
);

ALTER TABLE usuario_permissao ADD CONSTRAINT fk_usuper_on_permissao FOREIGN KEY (permissao_id) REFERENCES permissao (id);

ALTER TABLE usuario_permissao ADD CONSTRAINT fk_usuper_on_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id);

-- admin
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (1, 1);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (1, 2);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (1, 3);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (1, 4);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (1, 5);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (1, 6);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (1, 7);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (1, 8);

-- maria
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (2, 2);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (2, 5);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (2, 8);