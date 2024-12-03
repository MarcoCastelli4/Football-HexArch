package unibs.project.football.team.port.in.player;

import unibs.project.football.team.player.Player;
import unibs.project.football.team.port.in.team.TeamtNotFoundException;

public interface GetBestPlayerUseCase {
  Player getBestPlayer(String teamName) throws TeamtNotFoundException;
}
