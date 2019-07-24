package org.generation.jogo.Quiz.resposta;

import lombok.Data;
import org.generation.jogo.Quiz.pergunta.Pergunta;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Resposta {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_resposta;

    @ManyToOne
    @JoinColumn (name = "id_pergunta")
    private Pergunta id_pergunta;

    @NotNull
    private String alternativa;

    @NotNull
    private String descricao;

    @NotNull
    private boolean certa;
}
