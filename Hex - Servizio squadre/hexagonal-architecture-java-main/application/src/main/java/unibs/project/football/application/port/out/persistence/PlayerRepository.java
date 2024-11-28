package unibs.project.football.application.port.out.persistence;

import unibs.project.football.model.player.Player;

import java.util.List;

public interface PlayerRepository {

  void save(Player player);

  List<Player> getPlayerForTeam(String teamName);

  boolean getPlayer(Player player);


}
