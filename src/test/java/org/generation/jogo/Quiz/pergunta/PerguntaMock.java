package org.generation.jogo.Quiz.pergunta;

import com.github.javafaker.Faker;

import java.util.Locale;

public class PerguntaMock {
    public static Pergunta getPergunta() {
        Pergunta perguntaFake = new Pergunta();
        Faker faker = new Faker(new Locale("pt-BR"));

        perguntaFake.setNumero(faker.number().numberBetween(1, 1000));
        perguntaFake.setDescricao(faker.lorem().sentence());
        perguntaFake.setValorPontuacao(faker.number().numberBetween(1, 3));


        return perguntaFake;
    }
}
