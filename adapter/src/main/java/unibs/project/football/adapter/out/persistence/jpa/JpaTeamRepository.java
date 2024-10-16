package unibs.project.football.adapter.out.persistence.jpa;

import unibs.project.football.application.port.out.persistence.TeamRepository;
import unibs.project.football.model.team.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.Optional;

public class JpaTeamRepository implements TeamRepository {

  private final EntityManagerFactory entityManagerFactory;

  public JpaTeamRepository(EntityManagerFactory entityManagerFactory) {
    this.entityManagerFactory = entityManagerFactory;
  }

  @Override
  public void save(Team team) {
    try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
      entityManager.getTransaction().begin();
      entityManager.merge(TeamMapper.toJpaEntity(team));
      entityManager.getTransaction().commit();
    }
  }

  @Override
  public Optional<Team> findByTeamName(String name) {
    try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
      TeamJpaEntity cartJpaEntity = entityManager.find(TeamJpaEntity.class, name);
      return TeamMapper.toModelEntityOptional(cartJpaEntity);
    }
  }
}
