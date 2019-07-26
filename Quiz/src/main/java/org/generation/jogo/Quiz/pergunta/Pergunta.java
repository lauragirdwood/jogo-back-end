package org.generation.jogo.Quiz.pergunta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.generation.jogo.Quiz.QuizApplication;
import org.generation.jogo.Quiz.quiz.Quiz;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.print.Book;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "pergunta", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "numero"
        }),
        @UniqueConstraint(columnNames = {
                "descricao"
        }),
        @UniqueConstraint(columnNames = {
                "valor_pontuacao"
        })
})
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

    @ManyToMany(mappedBy = "perguntas")
    private Set<Quiz> quizs = new HashSet<>();

}
