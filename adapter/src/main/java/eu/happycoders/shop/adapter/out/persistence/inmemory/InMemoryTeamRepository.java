package eu.happycoders.shop.adapter.out.persistence.inmemory;

import eu.happycoders.shop.adapter.out.persistence.DemoTeam;
import eu.happycoders.shop.application.port.out.persistence.TeamRepository;
import eu.happycoders.shop.model.team.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class InMemoryTeamRepository implements TeamRepository {

    private final List<Team> teams=new ArrayList<>();

    public InMemoryTeamRepository() {
        createDemoTeam();
    }

    private void createDemoTeam() {
        DemoTeam.DEMO_TEAMS.forEach(this::save);
    }

    @Override
    public void save(Team team) {
        teams.add(team);
    }

    @Override
    public Optional<Team> findByTeamName(String name) {
        for (Team team : teams) {
            if (team.name().equalsIgnoreCase(name)) {
                return Optional.of(team); // Return the found team
            }
        }
        return Optional.empty(); // Return null if no team is found
    }

}
