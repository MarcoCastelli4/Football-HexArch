package unibs.project.football.adapter.out;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import unibs.project.football.adapter.PlayerDTO;
import unibs.project.football.adapter.PlayerMapper;
import unibs.project.football.application.port.out.AddPlayer;
import unibs.project.football.application.port.out.FindBestPlayerForTeam;
import unibs.project.football.model.player.Player;

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
        String playerServiceUrl = "http://localhost:8081/players";
        try {
            restTemplate.put(playerServiceUrl, playerMapper.toDTO(player));
        } catch (Exception e) {
            throw new RuntimeException("Failed to add player to Player Service: " + e.getMessage(), e);
        }
    }
}
