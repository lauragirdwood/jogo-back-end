package org.generation.jogo.Quiz.partida;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.generation.jogo.Quiz.jogador.Jogador;
import org.generation.jogo.Quiz.quiz.Quiz;
import javax.persistence.*;

@Getter @Setter
@Entity
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Jogador jogador;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_quiz")
    private Quiz quiz;

    private Boolean completa;
}
