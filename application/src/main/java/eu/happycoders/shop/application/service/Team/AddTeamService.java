package eu.happycoders.shop.application.service.Team;

import eu.happycoders.shop.application.port.in.team.AddTeamUseCase;
import eu.happycoders.shop.application.port.in.team.TeamAlreadyExistException;
import eu.happycoders.shop.application.port.in.team.TeamtNotFoundException;
import eu.happycoders.shop.application.port.out.persistence.TeamRepository;
import eu.happycoders.shop.model.player.Player;
import eu.happycoders.shop.model.team.Team;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AddTeamService implements AddTeamUseCase {

    private final TeamRepository teamRepository;

    public AddTeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    @Override
    public Team AddTeam(String name) throws TeamAlreadyExistException {
        Objects.requireNonNull(name, "'team name' must not be null");

        Optional<Team> team = teamRepository.findByTeamName(name);
        if (team.isPresent()) {
            throw new TeamAlreadyExistException("Team already present: " + name);
        }

        Team t= new Team(name);
        teamRepository.save(t);
        return t;


    }
}
