package org.generation.brazil.backend.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfile {

  private Long id;
  private String username;
  private String name;

}
