package unibs.project.football.player.port.in.player;

import unibs.project.football.player.Player;

public interface GetBestTeamPlayerUseCase {

  Player getBestTeamPlayer(String teamName);
}
