package unibs.project.football.application.port.out.persistence;

import unibs.project.football.model.team.Team;
import java.util.Optional;

public interface TeamRepository {

  void save(Team team);

  Optional<Team> findByTeamName(String name);
}
