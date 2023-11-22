CREATE TABLE lancamento (
  id BIGINT AUTO_INCREMENT NOT NULL,
   descricao VARCHAR(255) NOT NULL,
   data_vencimento date NOT NULL,
   data_pagamento date NULL,
   valor DECIMAL NOT NULL,
   observacao VARCHAR(255) NULL,
   tipo VARCHAR(255) NOT NULL,
   categoria_id BIGINT NOT NULL,
   pessoa_id BIGINT NOT NULL,
   CONSTRAINT pk_lancamento PRIMARY KEY (id)
);

ALTER TABLE lancamento ADD CONSTRAINT FK_LANCAMENTO_ON_CATEGORIA FOREIGN KEY (categoria_id) REFERENCES categoria (id);

ALTER TABLE lancamento ADD CONSTRAINT FK_LANCAMENTO_ON_PESSOA FOREIGN KEY (pessoa_id) REFERENCES pessoa (id);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id) values ('Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'RECEITA', 1, 1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id) values ('Supermercado', '2017-02-10', '2017-02-10', 100.32, null, 'DESPESA', 2, 2);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id) values ('Academia', '2017-06-10', null, 120, null, 'DESPESA', 3, 3);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id) values ('Conta de luz', '2017-02-10', '2017-02-10', 110.44, null, 'DESPESA', 3, 4);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id) values ('Conta de água', '2017-06-10', null, 200.30, null, 'DESPESA', 3, 5);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id) values ('Restaurante', '2017-03-10', '2017-03-10', 1010.32, null, 'DESPESA', 4, 6);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id) values ('Venda vídeo game', '2017-06-10', null, 500, null, 'RECEITA', 1, 7);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id) values ('Clube', '2017-03-10', '2017-03-10', 400.32, null, 'DESPESA', 4, 8);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id) values ('Impostos', '2017-06-10', null, 123.64, 'Multas', 'DESPESA', 3, 9);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id) values ('Multa', '2017-04-10', '2017-04-10', 665.33, null, 'DESPESA', 5, 10);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id) values ('Padaria', '2017-06-10', null, 8.32, null, 'DESPESA', 1, 5);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id) values ('Papelaria', '2017-04-10', '2017-04-10', 2100.32, null, 'DESPESA', 5, 4);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id) values ('Almoço', '2017-06-10', null, 1040.32, null, 'DESPESA', 4, 3);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id) values ('Café', '2017-04-10', '2017-04-10', 4.32, null, 'DESPESA', 4, 2);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id) values ('Lanche', '2017-06-10', null, 10.20, null, 'DESPESA', 4, 1);