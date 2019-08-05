package org.generation.jogo.Quiz.usuario;

import lombok.Getter;
import lombok.Setter;
import org.generation.jogo.Quiz.jogador.Jogador;
import javax.persistence.*;

@Getter @Setter
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id_usuario")
    private Long id;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Jogador jogador;

    private String username;

    private String senha;

    public Usuario() {
    }

    public Usuario(Jogador jogador, String username, String senha) {
        this.jogador = jogador;
        this.username = username;
        this.senha = senha;
    }
}

