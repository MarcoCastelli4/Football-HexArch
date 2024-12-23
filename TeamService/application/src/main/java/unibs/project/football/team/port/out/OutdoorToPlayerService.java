package unibs.project.football.team.port.out;

import org.springframework.http.ResponseEntity;
import unibs.project.football.team.player.Player;

public interface OutdoorToPlayerService {
  ResponseEntity<Player> getBestPlayer(String teamName);
}
