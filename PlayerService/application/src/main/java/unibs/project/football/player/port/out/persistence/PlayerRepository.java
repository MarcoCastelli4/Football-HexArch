package unibs.project.football.player.port.out.persistence;

import java.util.List;
import unibs.project.football.player.Player;

public interface PlayerRepository {

  void save(Player player);

  List<Player> getPlayerForTeam(String teamName);

  boolean getPlayer(Player player);
}
