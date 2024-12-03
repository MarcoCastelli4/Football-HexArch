package unibs.project.football.player.port.in.player;

import java.util.List;
import unibs.project.football.player.Player;

public interface GetPlayersUseCase {

  List<Player> getPlayers(String teamName);
}
