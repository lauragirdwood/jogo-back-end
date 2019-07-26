package org.generation.jogo.Quiz.quiz;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.generation.jogo.Quiz.jogador.Jogador;
import org.generation.jogo.Quiz.pergunta.Pergunta;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data

@EqualsAndHashCode(exclude = {"jogadores", "pergunta", "resposta", "usuario"})

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_quiz;

    @OneToMany
    private Set<Pergunta> perguntas = new HashSet<>();

    @NotNull
    private String nome;

    @NotNull
    private String tema;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "resultado",
    joinColumns = @JoinColumn(name = "id_quiz", referencedColumnName = "id_quiz"),
    inverseJoinColumns = @JoinColumn(name = "id_jogador", referencedColumnName = "id_jogador"))
    private Set<Jogador> jogadores;

    public Quiz(String nome, String tema, Jogador jogadores) {
        this.nome = nome;
        this.tema = tema;
        this.jogadores = Stream.of(jogadores).collect(Collectors.toSet());
        this.jogadores.forEach(x -> x.getQuizs().add(this));
    }

    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "resultado",
            joinColumns = @JoinColumn(name = "id_quiz", referencedColumnName = "id_quiz"),
            inverseJoinColumns = @JoinColumn(name = "id_pergunta", referencedColumnName = "id_pergunta"))
    private Set<Pergunta> perguntas; */

    public Quiz(String nome, String tema, Pergunta perguntas) {
        this.nome = nome;
        this.tema = tema;
        this.perguntas = Stream.of(perguntas).collect(Collectors.toSet());
        this.perguntas.forEach(x -> x.getQuizs().add(this));
    }




}
