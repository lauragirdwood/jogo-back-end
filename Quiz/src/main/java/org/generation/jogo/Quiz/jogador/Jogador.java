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

<<<<<<< HEAD
@Getter
@Setter
=======
@Getter @Setter
>>>>>>> 0c1dbe840b6532cb723d566f19cd5a4a25c45505
@Entity
public class Jogador {

    @Id
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "jogador")
    private Set<Partida> partidas = new HashSet<>();

    private String nome;

    private Integer pontuacao;

    private Integer nivel;

    public Jogador() {
    }

    public Jogador(Usuario usuario, String nome, Integer pontuacao, Integer nivel) {
        this.usuario = usuario;
        this.nome = nome;
        this.pontuacao = pontuacao;
        this.nivel = nivel;
    }
}
