package unibs.project.football.application.service.Team;

import java.util.Objects;
import java.util.Optional;
import unibs.project.football.application.port.in.team.AddTeamUseCase;
import unibs.project.football.application.port.in.team.TeamAlreadyExistException;
import unibs.project.football.application.port.out.persistence.TeamRepository;
import unibs.project.football.model.team.Team;

public class AddTeamService implements AddTeamUseCase {

  private final TeamRepository teamRepository;

  public AddTeamService(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  @Override
  public Team AddTeam(String name) throws TeamAlreadyExistException {
    Objects.requireNonNull(name, "'team name' must not be null");

    Optional<Team> team = teamRepository.findByTeamName(name);
    if (team.isPresent()) {
      throw new TeamAlreadyExistException("Team already present: " + name);
    }

    Team t = new Team(name);
    teamRepository.save(t);
    return t;
  }
}
