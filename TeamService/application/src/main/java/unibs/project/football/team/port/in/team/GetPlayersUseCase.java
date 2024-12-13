package unibs.project.football.team.port.in.team;

import unibs.project.football.team.player.Player;

import java.util.List;

public interface GetPlayersUseCase {

  List<Player> getPlayers(String name) throws TeamtNotFoundException;
}
