package org.generation.jogo.Quiz.pergunta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.generation.jogo.Quiz.quiz.Quiz;
import org.generation.jogo.Quiz.resposta.Resposta;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_quiz")
    private Quiz quiz;

    @OneToMany(mappedBy = "pergunta")
    private Set<Resposta> respostas = new HashSet<>();

    private Integer numero;

    private String descricao;

    @Column(name = "valor_pontuacao")
    private Integer valorPontuacao;
}
