package unibs.project.football.adapter.out.persistence.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/** */
@Table(name = "player")
@Getter
@Setter
@Entity
public class PlayerJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String role;

  @ManyToOne
  @JoinColumn(name = "id_team")
  private TeamJpaEntity team;
}
