package unibs.project.football.application.port.out;

import unibs.project.football.model.player.Player;

public interface FindBestPlayerForTeam {
 Player getBestPlayer(String teamName);
}
