package org.generation.jogo.Quiz.partida;

import com.github.javafaker.Faker;

import java.util.Locale;

public class PartidaMock {
    public static Partida getPartida() {
        Partida partidaFaker = new Partida();
        Faker faker = new Faker(new Locale("pt-BR"));

        partidaFaker.setCompleta(faker.bool().bool());

        return partidaFaker;
    }
}
