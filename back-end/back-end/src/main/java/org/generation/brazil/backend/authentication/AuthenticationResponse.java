package org.generation.brazil.backend.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class AuthenticationResponse {

  private Boolean success;
  private String message;

}
