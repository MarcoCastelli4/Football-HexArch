package unibs.project.football.team.adapter.out;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
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
  public String addPlayer(Player player) {
    String playerServiceUrl = "http://localhost:8090/player/addPlayer";
    try {

      restTemplate.put(playerServiceUrl, playerMapper.toDTO(player));
      return "Player added successfully";
    } catch (Exception e) {
      throw new RuntimeException("Failed to add player to Player Service: " + e.getMessage(), e);
    }
  }
}
