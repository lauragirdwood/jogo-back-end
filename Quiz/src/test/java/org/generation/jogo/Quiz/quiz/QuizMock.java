package org.generation.jogo.Quiz.quiz;

import com.github.javafaker.Faker;

import java.util.Locale;

public class QuizMock {
    public static Quiz getQuiz() {
        Quiz quizFaker = new Quiz();
        Faker faker = new Faker(new Locale("pt-BR"));

        quizFaker.setTema(faker.lordOfTheRings().location());
        quizFaker.setNome(faker.lordOfTheRings().character());

        return quizFaker;
    }
}
