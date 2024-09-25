package eu.happycoders.shop.application.service.Team;

import eu.happycoders.shop.application.port.in.team.GetPlayersUseCase;
import eu.happycoders.shop.application.port.in.team.TeamtNotFoundException;
import eu.happycoders.shop.application.port.out.persistence.TeamRepository;
import eu.happycoders.shop.model.player.Player;
import eu.happycoders.shop.model.team.Team;

import java.util.List;
import java.util.Objects;

public class GetPlayersService implements GetPlayersUseCase {

    private final TeamRepository teamRepository;

    public GetPlayersService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Player> getPlayers(String name) throws TeamtNotFoundException {
        Objects.requireNonNull(name, "'team name' must not be null");

        Team team = teamRepository.findByTeamName(name).orElseThrow(TeamtNotFoundException::new);


        return team.playerOfTeam();
    }


}
