package org.generation.brazil.backend.jogador;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Jogador {

  @Id
  private Long id;

  @NotBlank
  @Size(max = 100)
  private String nome;

  @NotBlank
  @Size(max = 100)
  private String sobrenome;

  @NotBlank
  @Size(max = 100)
  private String cidade;

}
