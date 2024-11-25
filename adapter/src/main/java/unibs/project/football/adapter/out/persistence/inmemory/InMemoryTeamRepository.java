package unibs.project.football.adapter.out.persistence.inmemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import unibs.project.football.adapter.out.persistence.DemoTeam;
import unibs.project.football.application.port.out.persistence.TeamRepository;
import unibs.project.football.model.team.Team;

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
    for (Team team : teams) {
      if (team.name().equalsIgnoreCase(name)) {
        return Optional.of(team); // Return the found team
      }
    }
    return Optional.empty(); // Return null if no team is found
  }
}
