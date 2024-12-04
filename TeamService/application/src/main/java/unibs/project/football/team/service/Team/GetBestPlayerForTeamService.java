package unibs.project.football.team.service.Team;

import java.util.ArrayList;
import java.util.List;
import unibs.project.football.team.player.Player;
import unibs.project.football.team.port.in.team.GetBestPlayerForTeamUseCase;
import unibs.project.football.team.port.out.OutdoorToPlayerService;
import unibs.project.football.team.port.out.persistence.TeamRepository;
import unibs.project.football.team.team.Team;

public class GetBestPlayerForTeamService implements GetBestPlayerForTeamUseCase {

  private final TeamRepository teamRepository;
  private final OutdoorToPlayerService bestTeamPlayer;

  public GetBestPlayerForTeamService(
          OutdoorToPlayerService bestTeamPlayer, TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
    this.bestTeamPlayer = bestTeamPlayer;
  }

  @Override
  public List<Player> getBestPlayerForTeamUseCase() {
    List<Team> teams = teamRepository.findAll();
    List<Player> players = new ArrayList<>();
    for (Team team : teams) {
      players.add(bestTeamPlayer.getBestPlayer(team.getName()));
    }
    return players;
  }
}
