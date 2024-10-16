package unibs.project.football.application.port.in.team;

import unibs.project.football.model.player.Player;
import java.util.List;

public interface GetPlayersUseCase {

  List<Player> getPlayers(String name) throws TeamtNotFoundException;
}
