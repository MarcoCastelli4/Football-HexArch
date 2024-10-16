package unibs.project.football.application.service.Team;

import unibs.project.football.application.port.in.team.GetPlayersUseCase;
import unibs.project.football.application.port.in.team.TeamtNotFoundException;
import unibs.project.football.application.port.out.persistence.TeamRepository;
import unibs.project.football.model.player.Player;
import unibs.project.football.model.team.Team;
import java.util.List;
import java.util.Objects;

public class GetPlayersService implements GetPlayersUseCase {

  private final TeamRepository teamRepository;

  public GetPlayersService(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  @Override
  public List<Player> getPlayers(String name) throws TeamtNotFoundException {
    Objects.requireNonNull(name, "'team name' must not be null");

    Team team = teamRepository.findByTeamName(name).orElseThrow(TeamtNotFoundException::new);

    return team.playerOfTeam();
  }
}
