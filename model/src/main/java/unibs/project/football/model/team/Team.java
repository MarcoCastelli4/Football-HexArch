package unibs.project.football.model.team;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import unibs.project.football.model.player.Player;

@Accessors(fluent = true)
@RequiredArgsConstructor
public class Team {
  private final List<Player> players = new ArrayList<>();
  @Getter private final String name;

  public Team(String name, List<Player> players) {
    this.name = name;
    this.players.addAll(players);
  }

  public void addPlayer(Player player) throws TeamHasMaxNumberOfPlayer {
    if (players.size() > 3) {
      throw new TeamHasMaxNumberOfPlayer(
          "Team %s is already completed! Assign player %s to another team"
              .formatted(name, player.name()),
          players.size());
    } else this.players.add(player);
  }

  public List<Player> playerOfTeam() {
    return List.copyOf(players);
  }
}
