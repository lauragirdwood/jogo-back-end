package org.generation.jogo.Quiz.jogador;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.generation.jogo.Quiz.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "jogador", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "id_usuario"
        }),
        @UniqueConstraint(columnNames = {
                "nome"
        }),
        @UniqueConstraint(columnNames = {
                "pontuacao"
        }),
        @UniqueConstraint(columnNames = {
                "nivel"
        })
})

public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_jogador;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario id_usuario;

    @NotNull
    private String nome;

    @NotNull
    private Integer pontuacao;

    @NotNull
    private Integer nivel;

}
