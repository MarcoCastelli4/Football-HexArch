package unibs.project.football.application.service.Team;

import java.util.Objects;
import unibs.project.football.application.port.in.team.AddPlayerToTeamUseCase;
import unibs.project.football.application.port.in.team.TeamtNotFoundException;
import unibs.project.football.application.port.out.persistence.TeamRepository;
import unibs.project.football.model.player.Player;
import unibs.project.football.model.team.Team;
import unibs.project.football.model.team.TeamHasMaxNumberOfPlayer;

public class AddPlayerToTeamService implements AddPlayerToTeamUseCase {

  private final TeamRepository teamRepository;

  public AddPlayerToTeamService(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  @Override
  public Team addPlayerToTeam(String name, Player player)
      throws TeamtNotFoundException, TeamHasMaxNumberOfPlayer {
    Objects.requireNonNull(name, "'team name' must not be null");

    Team team = teamRepository.findByTeamName(name).orElseThrow(TeamtNotFoundException::new);
    team.addPlayer(player);
    teamRepository.save(team);
    return team;
  }
}
