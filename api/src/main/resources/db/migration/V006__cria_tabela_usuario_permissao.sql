CREATE TABLE usuario_permissao (
  permissao_id BIGINT NOT NULL,
   usuario_id BIGINT NOT NULL
);

ALTER TABLE usuario_permissao ADD CONSTRAINT fk_usuper_on_permissao FOREIGN KEY (permissao_id) REFERENCES permissao (id);

ALTER TABLE usuario_permissao ADD CONSTRAINT fk_usuper_on_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id);