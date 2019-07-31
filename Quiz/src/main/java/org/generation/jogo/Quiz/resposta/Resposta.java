package org.generation.jogo.Quiz.resposta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.generation.jogo.Quiz.pergunta.Pergunta;
import javax.persistence.*;

@Getter @Setter
@Entity
public class Resposta {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn (name = "id_pergunta")
    private Pergunta pergunta;

    private String alternativa;

    private String descricao;

    private boolean certa;
}
