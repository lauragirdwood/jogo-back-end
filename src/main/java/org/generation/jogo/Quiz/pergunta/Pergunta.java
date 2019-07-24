package org.generation.jogo.Quiz.pergunta;

import lombok.Data;
import org.generation.jogo.Quiz.QuizApplication;
import org.generation.jogo.Quiz.quiz.Quiz;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pergunta;

    @ManyToOne
    @JoinColumn(name = "id_quiz")
    private Quiz id_quiz;

    @NotNull
    private Integer numero;

    @NotNull
    private String descricao;

    @NotNull
    private Integer valor_pontuacao;

}

