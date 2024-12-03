package unibs.project.football.player.player;

import java.util.List;
import java.util.Objects;
import unibs.project.football.player.Player;
import unibs.project.football.player.port.in.player.GetPlayersUseCase;
import unibs.project.football.player.port.out.persistence.PlayerRepository;

public class GetPlayersService implements GetPlayersUseCase {

  private final PlayerRepository playerRepository;

  public GetPlayersService(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public List<Player> getPlayers(String teamName) {
    Objects.requireNonNull(teamName, "'team name' must not be null");
    return playerRepository.getPlayerForTeam(teamName);
  }
}
