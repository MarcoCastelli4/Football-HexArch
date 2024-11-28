package unibs.project.football.application.port.in.player;

import java.util.List;
import unibs.project.football.model.player.Player;

public interface GetPlayersUseCase {

  List<Player> getPlayers(String teamName);
}