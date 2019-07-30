package org.generation.jogo.Quiz.usuario;

import lombok.Getter;
import lombok.Setter;
import org.generation.jogo.Quiz.jogador.Jogador;
import javax.persistence.*;

@Getter @Setter
@Entity
public class Usuario {

    @Id
    @Column( name = "id_usuario")
    private Long idUsuario;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Jogador jogador;

    private String username;

    private String senha;
}
