package unibs.project.football.team.adapter.out;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import unibs.project.football.team.adapter.PlayerDTO;
import unibs.project.football.team.adapter.PlayerMapper;
import unibs.project.football.team.player.Player;

@Component
public class OutdoorToPlayerService
    implements unibs.project.football.team.port.out.OutdoorToPlayerService {

  private final RestTemplate restTemplate;
  private final PlayerMapper playerMapper;

  public OutdoorToPlayerService(RestTemplate restTemplate, PlayerMapper playerMapper) {
    this.restTemplate = restTemplate;
    this.playerMapper = playerMapper;
  }

  @Override
  public ResponseEntity<Player> getBestPlayer(String teamName) {
    try {
      String url = "http://localhost:8090/player/" + teamName + "/bestPlayer";
      PlayerDTO playerDTO = restTemplate.getForObject(url, PlayerDTO.class);

      if (playerDTO != null) {
        // Convert the PlayerDTO to a Player object using a mapper
        Player player = playerMapper.toEntity(playerDTO);
        return ResponseEntity.ok(player); // Return ResponseEntity with the Player object
      } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Return a BAD_REQUEST status
    }
  }

}
