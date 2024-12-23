package unibs.project.football.team.port.in.team;

import java.util.List;
import unibs.project.football.team.player.Player;

public interface GetPlayersUseCase {

  List<Player> getPlayers(String name) throws TeamtNotFoundException;
}
