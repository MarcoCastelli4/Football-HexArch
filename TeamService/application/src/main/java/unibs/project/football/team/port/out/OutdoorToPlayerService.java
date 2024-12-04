package unibs.project.football.team.port.out;

import unibs.project.football.team.player.Player;

public interface OutdoorToPlayerService {
  Player getBestPlayer(String teamName);
}
