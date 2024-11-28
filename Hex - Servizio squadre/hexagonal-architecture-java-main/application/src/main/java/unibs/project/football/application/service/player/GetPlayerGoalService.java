package unibs.project.football.application.service.player;

import unibs.project.football.application.port.in.player.GetBestTeamPlayerUseCase;
import unibs.project.football.application.port.out.persistence.PlayerRepository;
import unibs.project.football.model.player.Player;

import java.util.List;

public class GetPlayerGoalService implements GetBestTeamPlayerUseCase {

  private final PlayerRepository playerRepository;

  public GetPlayerGoalService(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public Player getBestTeamPlayer(String teamName) {
    List<Player> players = playerRepository.getPlayerForTeam(teamName);

    Player bestPlayer = null;
    int maxGoals = -1;

    if(!players.isEmpty()) {
      for (Player player : players) {
        int goals = player.getGoal();
        if (goals > maxGoals) {
          maxGoals = goals;
          bestPlayer = player;
        }
      }
    }
    return bestPlayer;
  }
}
