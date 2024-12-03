package unibs.project.football.team.adapter.out;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import unibs.project.football.team.adapter.PlayerMapper;
import unibs.project.football.team.player.Player;
import unibs.project.football.team.port.out.AddPlayer;

@Service
public class AddPlayerToTeamController implements AddPlayer {

  private final RestTemplate restTemplate;
  private final PlayerMapper playerMapper;

  public AddPlayerToTeamController(RestTemplate restTemplate, PlayerMapper playerMapper) {
    this.restTemplate = restTemplate;
    this.playerMapper = playerMapper;
  }

  @Override
  public void addPlayer(Player player) {
    String playerServiceUrl = "http://localhost:8081/player/addPlayer";
    try {

      restTemplate.put(playerServiceUrl, playerMapper.toDTO(player));
    } catch (Exception e) {
      throw new RuntimeException("Failed to add player to Player Service: " + e.getMessage(), e);
    }
  }
}
