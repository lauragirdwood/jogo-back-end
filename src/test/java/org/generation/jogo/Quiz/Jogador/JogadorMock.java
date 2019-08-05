package org.generation.jogo.Quiz.Jogador;

import com.github.javafaker.Faker;
import org.generation.jogo.Quiz.jogador.Jogador;
import java.util.Locale;


public class JogadorMock {
    public static Jogador getJogador() {
        Jogador jogadorFake = new Jogador();
        Faker faker = new Faker(new Locale("pt-BR"));

        jogadorFake.setNome(faker.name().fullName());
        jogadorFake.setPontuacao(faker.number().numberBetween(1, 3));
        jogadorFake.setNivel(faker.number().numberBetween(1, 3));

        return jogadorFake;
    }
}
