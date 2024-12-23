package unibs.project.football.team.port.out;

import org.springframework.http.ResponseEntity;
import unibs.project.football.team.player.Player;

public interface AddPlayer {
  ResponseEntity<String> addPlayer(Player player);
}
