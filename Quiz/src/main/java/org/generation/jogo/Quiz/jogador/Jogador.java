package org.generation.jogo.Quiz.jogador;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.generation.jogo.Quiz.partida.Partida;
import org.generation.jogo.Quiz.usuario.Usuario;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Entity
public class Jogador {

    @Id
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_usuario")
    @MapsId
    private Usuario usuario;

    @OneToMany(mappedBy = "jogador")
    private Set<Partida> partidas = new HashSet<>();

    private String nome;

    private String fotoUrl;

    private Integer pontuacao;

    private Integer nivel;
}
