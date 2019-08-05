package org.generation.jogo.Quiz;

import org.generation.jogo.Quiz.jogador.Jogador;
import org.generation.jogo.Quiz.jogador.JogadorController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "org.generation.jogo.Quiz.config")
public class QuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

}
