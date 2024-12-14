package unibs.project.football.team.adapter.out;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import unibs.project.football.team.adapter.PlayerDTO;
import unibs.project.football.team.adapter.PlayerDTOEr;
import unibs.project.football.team.adapter.PlayerMapper;
import unibs.project.football.team.player.Player;

@Component
public class OutdoorToPlayerService implements unibs.project.football.team.port.out.OutdoorToPlayerService {

  private final RestTemplate restTemplate;
  private final PlayerMapper playerMapper;

  public OutdoorToPlayerService(RestTemplate restTemplate, PlayerMapper playerMapper) {
    this.restTemplate = restTemplate;
    this.playerMapper = playerMapper;
  }

  @Override
  public Player getBestPlayer(String teamName) {
    try {
      String url = "http://localhost:8090" + "/player/" + teamName + "/bestPlayer";
     PlayerDTO playerDTO = restTemplate.getForObject(url, PlayerDTO.class);

      //PlayerDTOEr playerDTO=restTemplate.getForObject(url, PlayerDTOEr.class);
      // Convert the PlayerDTO to a Player object using a mapper
      if (playerDTO != null) {
        return playerMapper.toEntity(playerDTO); // Use your PlayerMapper
        //return new Player();
      } else {
        throw new RuntimeException("PlayerDTO response is null");
      }
    } catch (Exception e) {
      throw new RuntimeException("Error fetching the best player", e);
    }
  }
}