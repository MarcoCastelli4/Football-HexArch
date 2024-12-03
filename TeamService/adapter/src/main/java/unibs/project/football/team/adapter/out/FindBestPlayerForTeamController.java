package unibs.project.football.team.adapter.out;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import unibs.project.football.team.adapter.PlayerDTO;
import unibs.project.football.team.adapter.PlayerMapper;
import unibs.project.football.team.player.Player;
import unibs.project.football.team.port.out.FindBestPlayerForTeam;

@Service
public class FindBestPlayerForTeamController implements FindBestPlayerForTeam {

  private final RestTemplate restTemplate;
  private final PlayerMapper playerMapper;

  public FindBestPlayerForTeamController(RestTemplate restTemplate, PlayerMapper playerMapper) {
    this.restTemplate = restTemplate;
    this.playerMapper = playerMapper;
  }

  @Override
  public Player getBestPlayer(String teamName) {
    try {
      String url = "http://localhost:8081" + "/player/" + teamName + "/bestPlayer";
      PlayerDTO playerDTO = restTemplate.getForObject(url, PlayerDTO.class);

      // Convert the PlayerDTO to a Player object using a mapper
      if (playerDTO != null) {
        return playerMapper.toEntity(playerDTO); // Use your PlayerMapper
      } else {
        throw new RuntimeException("PlayerDTO response is null");
      }
    } catch (Exception e) {
      throw new RuntimeException("Error fetching the best player", e);
    }
  }
}
