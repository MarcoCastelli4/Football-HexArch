package unibs.project.football.adapter.out.persistence.jpa;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/** */
@Table(name = "teams")
@Getter
@Setter
@Entity
public class TeamJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  // Utilizza PlayerJpaEntity per la persistenza
  @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PlayerJpaEntity> players = new ArrayList<>();
}
