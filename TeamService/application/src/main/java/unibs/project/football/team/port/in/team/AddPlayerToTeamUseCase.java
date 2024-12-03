package unibs.project.football.team.port.in.team;

import unibs.project.football.team.player.Player;
import unibs.project.football.team.team.Team;
import unibs.project.football.team.team.TeamHasMaxNumberOfPlayer;

public interface AddPlayerToTeamUseCase {

  Team addPlayerToTeam(String name, Player player)
      throws TeamtNotFoundException, TeamHasMaxNumberOfPlayer;
}
