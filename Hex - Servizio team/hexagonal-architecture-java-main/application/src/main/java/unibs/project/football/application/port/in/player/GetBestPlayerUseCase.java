package unibs.project.football.application.port.in.player;

import unibs.project.football.application.port.in.team.TeamtNotFoundException;
import unibs.project.football.model.player.Player;

public interface GetBestPlayerUseCase {
    Player getBestPlayer(String teamName) throws TeamtNotFoundException;
}
