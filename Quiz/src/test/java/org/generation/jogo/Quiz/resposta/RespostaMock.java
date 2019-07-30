/*package org.generation.jogo.Quiz.resposta;

import com.github.javafaker.Faker;
import org.generation.jogo.Quiz.pergunta.Pergunta;
import java.util.Locale;

public class RespostaMock {
    public static Resposta getResposta() {
        Resposta respostaFake = new Resposta();
        Faker faker = new Faker(new Locale("pt-BR"));

        respostaFake.setAlternativa(faker.numerify("2"));
        respostaFake.setDescricao(faker.lorem().sentence());
        respostaFake.setCerta(faker.bool().bool());

        return respostaFake;
    }
}*/
