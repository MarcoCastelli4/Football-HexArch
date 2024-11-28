package unibs.project.football.application.service.Team;

import unibs.project.football.application.port.in.team.GetBestPlayerForTeamUseCase;
import unibs.project.football.application.port.out.FindBestPlayerForTeam;
import unibs.project.football.application.port.out.persistence.TeamRepository;
import unibs.project.football.model.player.Player;
import unibs.project.football.model.team.Team;

import java.util.ArrayList;
import java.util.List;

public class GetBestPlayerForTeamService implements GetBestPlayerForTeamUseCase {

    private final TeamRepository teamRepository;
    private final FindBestPlayerForTeam bestTeamPlayer;

    public GetBestPlayerForTeamService(FindBestPlayerForTeam bestTeamPlayer, TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
        this.bestTeamPlayer=bestTeamPlayer;
    }


    @Override
    public List<Player> getBestPlayerForTeamUseCase() {
        List<Team> teams = teamRepository.findAll();
        List<Player> players = new ArrayList<>();
        for (Team team : teams) {
            players.add(bestTeamPlayer.getBestPlayer(team.getName()));
        }
        return players;
    }
}

