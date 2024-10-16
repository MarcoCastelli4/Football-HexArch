package unibs.project.football.application.port.in.team;

import unibs.project.football.model.player.Player;
import unibs.project.football.model.team.Team;
import unibs.project.football.model.team.TeamHasMaxNumberOfPlayer;

public interface AddPlayerToTeamUseCase {

  Team addPlayerToTeam(String name, Player player)
      throws TeamtNotFoundException, TeamHasMaxNumberOfPlayer;
}
