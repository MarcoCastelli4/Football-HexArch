package unibs.project.football.application.service.Team;

import unibs.project.football.application.port.in.team.InsertPlayerToTeamUseCase;
import unibs.project.football.application.port.in.team.TeamtNotFoundException;
import unibs.project.football.application.port.out.AddPlayer;
import unibs.project.football.application.port.out.FindBestPlayerForTeam;
import unibs.project.football.application.port.out.persistence.TeamRepository;
import unibs.project.football.model.player.Player;
import unibs.project.football.model.team.Team;
import unibs.project.football.model.team.TeamHasMaxNumberOfPlayer;

public class InsertPlayerToTeamService implements InsertPlayerToTeamUseCase {

    private final TeamRepository teamRepository;
    private final AddPlayer addPlayer;


    public InsertPlayerToTeamService(TeamRepository teamRepository,AddPlayer addPlayer) {
        this.teamRepository = teamRepository;
        this.addPlayer = addPlayer;
    }

    @Override
    public void insertPlayerToTeam(String name, Player player) {
        // Se il team non esiste lo aggiungo e dopo aggiungo anche il giocatore
        if(teamRepository.findByTeamName(name).isEmpty()) {
            teamRepository.save(new Team(name));
        }
        addPlayer.addPlayer(player);
    }
}

