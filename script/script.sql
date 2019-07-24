CREATE DATABASE jogo;
USE jogo;

CREATE TABLE quiz (
	id_quiz INTEGER NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    tema VARCHAR(255),
    PRIMARY KEY (id_quiz)
);

CREATE TABLE pergunta (
    id_pergunta INTEGER NOT NULL AUTO_INCREMENT,
    id_quiz INTEGER,
    numero INTEGER NOT NULL,
    descricao TEXT NOT NULL,
    valor_pontuacao INTEGER NOT NULL,
    PRIMARY KEY (id_pergunta),
    FOREIGN KEY (id_quiz) REFERENCES quiz(id_quiz)
);

CREATE TABLE resultado (
    id_resultado INTEGER NOT NULL AUTO_INCREMENT,
    completo BOOLEAN NOT NULL,
    id_quiz INTEGER,
    id_pergunta INTEGER,
    id_resposta INTEGER,
    id_jogador INTEGER,
    PRIMARY KEY (id_resultado),
    FOREIGN KEY (id_quiz) REFERENCES quiz(id_quiz),
    FOREIGN KEY (id_pergunta) REFERENCES pergunta(id_pergunta),
    FOREIGN KEY (id_resposta) REFERENCES resposta(id_resposta),
    FOREIGN KEY (id_jogador) REFERENCES jogadore(id_jogador)

CREATE TABLE resposta(
    id_resposta INTEGER NOT NULL AUTO_INCREMENT,
    id_pergunta INTEGER NOT NULL,
    alternativa VARCHAR(2) NOT NULL,
    descricao TEXT NOT NULL,
    certa BOOLEAN NOT NULL,
    PRIMARY KEY (id_resposta),
    FOREIGN KEY (id_pergunta) REFERENCES pergunta(id_pergunta)
);

CREATE TABLE jogador (
id_jogador INTEGER NOT NULL AUTO_INCREMENT,
id_usuario INTEGER NOT NULL,
nome VARCHAR(255) NOT NULL,
foto_url VARCHAR(255),
pontuacao INTEGER NOT NULL,
nivel INTEGER NOT NULL
PRIMARY KEY (id_jogador),
FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE usuario (
id_usuario INTEGER NOT NULL AUTO_INCREMENT,
username VARCHAR(50) NOT NULL,
senha VARCHAR(100) NOT NULL,
PRIMARY KEY (id_usuario)
);
