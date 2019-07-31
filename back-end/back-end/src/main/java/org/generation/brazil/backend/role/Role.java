package org.generation.brazil.backend.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Getter @Setter
@Entity
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NaturalId
  @Column(length = 60)
  @Enumerated(EnumType.STRING)
  private RoleName name;

}
