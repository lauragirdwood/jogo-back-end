package org.generation.jogo.Quiz.quiz;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.generation.jogo.Quiz.partida.Partida;
import org.generation.jogo.Quiz.pergunta.Pergunta;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "quiz")
    private Set<Pergunta> perguntas = new HashSet<>();

    @OneToMany(mappedBy = "quiz")
    private Set<Partida> partidas = new HashSet<>();

    private String nome;

    private String tema;
}
