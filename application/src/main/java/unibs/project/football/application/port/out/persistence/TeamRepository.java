package unibs.project.football.application.port.out.persistence;

import java.util.Optional;
import unibs.project.football.model.team.Team;

public interface TeamRepository {

  void save(Team team);

  Optional<Team> findByTeamName(String name);
}
