package unibs.project.football.team.adapter.out;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import unibs.project.football.team.adapter.PlayerMapper;
import unibs.project.football.team.player.Player;
import unibs.project.football.team.port.out.AddPlayer;

@Component
public class OutdoorAddPlayerToTeam implements AddPlayer {

  private final RestTemplate restTemplate;
  private final PlayerMapper playerMapper;

  public OutdoorAddPlayerToTeam(RestTemplate restTemplate, PlayerMapper playerMapper) {
    this.restTemplate = restTemplate;
    this.playerMapper = playerMapper;
  }

  @Override
  public ResponseEntity<String> addPlayer(Player player) {
    String playerServiceUrl = "http://localhost:8090/player/addPlayer";
    try {

      restTemplate.put(playerServiceUrl, playerMapper.toDTO(player));

      return ResponseEntity.ok("Player added successfully");
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
