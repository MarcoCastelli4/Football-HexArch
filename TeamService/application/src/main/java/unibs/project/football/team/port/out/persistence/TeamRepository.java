package unibs.project.football.team.port.out.persistence;

import java.util.List;
import java.util.Optional;
import unibs.project.football.team.team.Team;

public interface TeamRepository {

  void save(Team team);

  Optional<Team> findByTeamName(String name);

  List<Team> findAll();
}
