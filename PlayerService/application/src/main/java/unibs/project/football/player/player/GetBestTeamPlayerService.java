package unibs.project.football.player.player;

import java.util.List;
import unibs.project.football.player.Player;
import unibs.project.football.player.port.in.player.GetBestTeamPlayerUseCase;
import unibs.project.football.player.port.out.persistence.PlayerRepository;

public class GetBestTeamPlayerService implements GetBestTeamPlayerUseCase {

  private final PlayerRepository playerRepository;

  public GetBestTeamPlayerService(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public Player getBestTeamPlayer(String teamName) {
    List<Player> players = playerRepository.getPlayerForTeam(teamName);

    Player bestPlayer = null;
    int maxGoals = -1;

    if (!players.isEmpty()) {
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
