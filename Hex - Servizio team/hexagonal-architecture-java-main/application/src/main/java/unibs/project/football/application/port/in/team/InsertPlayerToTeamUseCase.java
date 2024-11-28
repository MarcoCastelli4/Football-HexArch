package unibs.project.football.application.port.in.team;

import unibs.project.football.model.player.Player;
import unibs.project.football.model.team.Team;
import unibs.project.football.model.team.TeamHasMaxNumberOfPlayer;

public interface InsertPlayerToTeamUseCase {

  void insertPlayerToTeam(String name,Player player);
}
