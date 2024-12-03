package unibs.project.football.team.port.out;

import unibs.project.football.team.player.Player;

public interface FindBestPlayerForTeam {
  Player getBestPlayer(String teamName);
}
