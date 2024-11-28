package unibs.project.football.adapter.out.persistence.inmemory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import unibs.project.football.adapter.out.persistence.DemoPlayer;
import unibs.project.football.application.port.out.persistence.PlayerRepository;
import unibs.project.football.model.player.Player;

import java.util.*;

@ConditionalOnProperty(name = "persistence", havingValue = "inmemory", matchIfMissing = true)
@Repository
public class InMemoryPlayerRepository implements PlayerRepository {

  private final List<Player> player = new ArrayList<>();

  public InMemoryPlayerRepository() {
    createDemoGoal();
  }

  private void createDemoGoal() {
    DemoPlayer.DEMO_PLAYER.forEach(this::save);
  }

  @Override
  public void save(Player player) {
    this.player.add(player);
  }

  @Override
  public List<Player> getPlayerForTeam(String teamName) {
    List<Player> players = new ArrayList<>();
    for (Player player : this.player) {
      if (player.getTeam().equals(teamName)) {
        players.add(player);
      }
    }
    return players;
  }

  @Override
  public boolean getPlayer(Player player) {
    return this.player.contains(player);
  }



}

