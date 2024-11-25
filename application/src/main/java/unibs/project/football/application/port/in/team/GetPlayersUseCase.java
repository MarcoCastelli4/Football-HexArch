package unibs.project.football.application.port.in.team;

import java.util.List;
import unibs.project.football.model.player.Player;

public interface GetPlayersUseCase {

  List<Player> getPlayers(String name) throws TeamtNotFoundException;
}
