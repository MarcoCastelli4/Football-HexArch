package unibs.project.football.application.port.in.player;

import unibs.project.football.model.player.Player;

public interface GetBestTeamPlayerUseCase {

  Player getBestTeamPlayer(String teamName);
}
