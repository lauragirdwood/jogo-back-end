package org.generation.jogo.Quiz.resultado;

/* import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.generation.jogo.Quiz.pergunta.Pergunta;
import org.generation.jogo.Quiz.quiz.Quiz;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "resultado", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "completo"
        })
})
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_resultado;

    @OneToMany
    @JoinColumn(name = "id_quiz")
    private Quiz id_quiz;

    @ManyToMany
    @JoinColumn(name = "id_pergunta")
    private Pergunta id_pergunta;

    @ManyToMany
    @JoinColumn(name = "id_resposta")
    //private Resposta id_resposta;

    @OneToMany
    @JoinColumn(name = "id_jogador")
    //private Jogador id_jogador;

    @NotBlank
    private Boolean completo;

} */
