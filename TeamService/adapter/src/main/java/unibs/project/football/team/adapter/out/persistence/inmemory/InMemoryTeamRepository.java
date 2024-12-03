package unibs.project.football.team.adapter.out.persistence.inmemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import unibs.project.football.team.adapter.out.persistence.DemoTeam;
import unibs.project.football.team.port.out.persistence.TeamRepository;
import unibs.project.football.team.team.Team;

@ConditionalOnProperty(name = "persistence", havingValue = "inmemory", matchIfMissing = true)
@Repository
public class InMemoryTeamRepository implements TeamRepository {

  private final List<Team> teams = new ArrayList<>();

  public InMemoryTeamRepository() {
    createDemoTeam();
  }

  private void createDemoTeam() {
    DemoTeam.DEMO_TEAMS.forEach(this::save);
  }

  @Override
  public void save(Team team) {
    teams.add(team);
  }

  @Override
  public Optional<Team> findByTeamName(String name) {
    return teams.stream().filter(team -> team.getName().equals(name)).findFirst();
  }

  @Override
  public List<Team> findAll() {
    return teams;
  }
}
