package org.generation.brazil.backend.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UserProfile {

  private Long id;
  private String username;
  private String name;

}
