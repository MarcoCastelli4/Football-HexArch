package unibs.project.football.player.player;

import unibs.project.football.player.Player;
import unibs.project.football.player.port.in.player.AddPlayerUseCase;
import unibs.project.football.player.port.out.persistence.PlayerRepository;

public class AddPlayerService implements AddPlayerUseCase {

  private final PlayerRepository playerRepository;

  public AddPlayerService(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public boolean addPlayer(Player player) {
    if (!playerRepository.getPlayer(player)) {
      playerRepository.save(player);
      return true;
    }
    return false;
  }
}
