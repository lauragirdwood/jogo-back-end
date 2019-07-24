package org.generation.jogo.Quiz.resposta;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.generation.jogo.Quiz.pergunta.Pergunta;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "resposta", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "alternativa"
        }),
        @UniqueConstraint(columnNames = {
                "descricao"
        }),
        @UniqueConstraint(columnNames = {
                "certa"
        })
})
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
