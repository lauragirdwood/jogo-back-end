package org.generation.brazil.backend.security;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignUpRequest {

  @NotBlank
  @Size(min = 4, max = 40)
  private String name;

  @NotBlank
  @Size(min = 3, max = 15)
  private String username;

  @Email
  @NotBlank
  @Size(max = 40)
  private String email;

  @NotBlank
  @Size(min = 6, max = 20)
  private String password;

}
