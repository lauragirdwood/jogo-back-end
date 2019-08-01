package org.generation.brazil.backend.jogador;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.generation.brazil.backend.user.User;

import java.util.HashSet;

@Getter @Setter
@Entity
public class Jogador {

  @Id
  private Long id;

  @JsonIgnore
  @OneToOne
  @JoinColumn(name = "id_user")
  @MapsId
  private User user;

  @OneToMany(mappedBy = "jogador")
  private Set<Partida> partidas = new HashSet<>();

  @NotBlank
  @Size(max = 100)
  private String nome;

  @Size(max = 200)
  private String fotoUrl;

  @NotBlank
  private Integer pontuacao;

  @NotBlank
  private Integer nivel;

}
