package eu.happycoders.shop.model.team;

import eu.happycoders.shop.model.player.Player;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Accessors(fluent = true)
@RequiredArgsConstructor
public class Team {
   private final List<Player> players = new ArrayList<>();
   @Getter
   private final String name;

    public Team(String name, List<Player> players) {
        this.name = name;
        this.players.addAll(players);
    }

    public void addPlayer(Player player) throws TeamHasMaxNumberOfPlayer {
        if(players.size()>3)
        {
            throw new TeamHasMaxNumberOfPlayer("Team %s is already completed! Assign player %s to another team".formatted(name, player.name()),players.size());
        }
        else this.players.add(player);
    }

    public List<Player> playerOfTeam() {
        return List.copyOf(players);
    }

}
