package unibs.project.football.team.port.in.team;

import unibs.project.football.team.player.Player;

public interface InsertPlayerToTeamUseCase {

  void insertPlayerToTeam(String name, Player player);
}
