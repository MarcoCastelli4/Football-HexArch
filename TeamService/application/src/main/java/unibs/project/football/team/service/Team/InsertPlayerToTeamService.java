package unibs.project.football.team.service.Team;

import unibs.project.football.team.player.Player;
import unibs.project.football.team.port.in.team.InsertPlayerToTeamUseCase;
import unibs.project.football.team.port.out.AddPlayer;
import unibs.project.football.team.port.out.persistence.TeamRepository;
import unibs.project.football.team.team.Team;

public class InsertPlayerToTeamService implements InsertPlayerToTeamUseCase {

  private final TeamRepository teamRepository;
  private final AddPlayer addPlayer;

  public InsertPlayerToTeamService(TeamRepository teamRepository, AddPlayer addPlayer) {
    this.teamRepository = teamRepository;
    this.addPlayer = addPlayer;
  }

  @Override
  public void insertPlayerToTeam(String name, Player player) {
    // Se il team non esiste lo aggiungo e dopo aggiungo anche il giocatore
    if (teamRepository.findByTeamName(name).isEmpty()) {
      teamRepository.save(new Team(name));
    }
    addPlayer.addPlayer(player);
  }
}
