package unibs.project.football.adapter.out;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import unibs.project.football.adapter.PlayerDTO;
import unibs.project.football.adapter.PlayerMapper;
import unibs.project.football.application.port.out.FindBestPlayerForTeam;
import unibs.project.football.model.player.Player;

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
            String url = "http://localhost:8081" + "/" + teamName + "/bestPlayer";
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
