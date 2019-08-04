use jogo2;
show tables;

describe jogador;

describe quiz;
INSERT INTO quiz(nome, tema) VALUES ('Operadores Lógicos', 'Lógica');
SELECT * FROM quiz;

describe pergunta;
INSERT INTO pergunta(descricao, numero, valor_pontuacao, id_quiz) VALUES ('Qual dos operadores abaixo representa o operador E (em inglês, AND)?', 1, 2, 1);
INSERT INTO pergunta(descricao, numero, valor_pontuacao, id_quiz) VALUES ('Qual das alternativas abaixo é o equivalente a dizer "Marcelo NÃO é bonito"?', 2, 2, 1);
INSERT INTO pergunta(descricao, numero, valor_pontuacao, id_quiz) VALUES ('Qual dos operadores abaixo representa o operador OU (em inglês, OR)?', 3, 2, 1);
INSERT INTO pergunta(descricao, numero, valor_pontuacao, id_quiz) VALUES ('Minha mãe só come pastel de pizza OU de palmito. Qual das alternativas abaixo é o equivalente a dizer "pizza OU palmito"?', 4, 2, 1);
INSERT INTO pergunta(descricao, numero, valor_pontuacao, id_quiz) VALUES ('Meu pai só almoça se o arroz E o feijão se estiverem juntos. Qual das alternativas abaixo meu pai poderá comer?', 5, 2, 1);

SELECT * FROM pergunta;

describe resposta;
INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('a', 0, '!=', 1);
INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('b', 0, '||', 1);
INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('c', 1, '&&', 1);
INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('d', 0, '==', 1);


INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('a', 1, 'Marcelo != bonito. ', 2);
INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('b', 0, 'Marcelo || bonito.', 2);
INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('c', 0, 'Marcelo && bonito.', 2);
INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('d', 0, 'Marcelo == bonito.', 2);

INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('a', 0, '!=', 3);
INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('b', 1, '||', 3);
INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('c', 0, '&&', 3);
INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('d', 0, '==', 3);

INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('a', 0, 'pizza != palmito.', 4);
INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('b', 1, 'pizza || palmito.', 4);
INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('c', 0, 'pizza && palmito.', 4);
INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('d', 0, 'pizza == palmito.', 4);

INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('a', 0, 'arroz != feijão', 5);
INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('b', 0, 'arroz || feijão', 5);
INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('c', 0, 'arroz && feijão', 5);
INSERT INTO resposta(alternativa, certa, descricao, id_pergunta) VALUES ('d', 1, 'arroz == feijão', 5);

SELECT * FROM resposta;

SELECT pergunta.numero, pergunta.descricao, resposta.alternativa, resposta.descricao, resposta.certa 
FROM pergunta
INNER JOIN resposta
ON pergunta.id = resposta.id_pergunta;