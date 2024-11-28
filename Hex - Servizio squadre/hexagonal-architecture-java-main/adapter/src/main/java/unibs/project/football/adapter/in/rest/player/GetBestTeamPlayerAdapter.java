package unibs.project.football.adapter.in.rest.player;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import unibs.project.football.adapter.out.persistence.PlayerDTO;
import unibs.project.football.adapter.out.persistence.PlayerMapper;
import unibs.project.football.application.port.in.player.GetBestTeamPlayerUseCase;
import unibs.project.football.model.player.Player;

@RestController
public class GetBestTeamPlayerAdapter {

    private final GetBestTeamPlayerUseCase getBestTeamPlayerUseCase;
    private final PlayerMapper playerMapper;

    public GetBestTeamPlayerAdapter(GetBestTeamPlayerUseCase getBestTeamPlayerUseCase, PlayerMapper playerMapper) {
        this.getBestTeamPlayerUseCase = getBestTeamPlayerUseCase;
        this.playerMapper = playerMapper;
    }

    @GetMapping("/{teamName}/bestPlayer")
    public ResponseEntity<Object> getGoals(@PathVariable String teamName) {
        return ResponseEntity.ok(playerMapper.toDTO(getBestTeamPlayerUseCase.getBestTeamPlayer(teamName)));
    }
}
