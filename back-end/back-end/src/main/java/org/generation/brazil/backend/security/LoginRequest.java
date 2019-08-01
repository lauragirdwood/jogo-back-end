package org.generation.brazil.backend.security;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequest {

  @NotBlank
  private String usernameOrEmail;

  @NotBlank
  private String password;

}
