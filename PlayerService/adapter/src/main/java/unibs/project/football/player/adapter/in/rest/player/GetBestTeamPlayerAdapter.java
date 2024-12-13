package unibs.project.football.player.adapter.in.rest.player;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unibs.project.football.player.adapter.out.persistence.PlayerDTO;
import unibs.project.football.player.adapter.out.persistence.PlayerMapper;
import unibs.project.football.player.port.in.player.GetBestTeamPlayerUseCase;

@RestController
@RequestMapping("/player")
public class GetBestTeamPlayerAdapter {

  private final GetBestTeamPlayerUseCase getBestTeamPlayerUseCase;
  private final PlayerMapper playerMapper;

  public GetBestTeamPlayerAdapter(
      GetBestTeamPlayerUseCase getBestTeamPlayerUseCase, PlayerMapper playerMapper) {
    this.getBestTeamPlayerUseCase = getBestTeamPlayerUseCase;
    this.playerMapper = playerMapper;
  }

  @GetMapping("/{teamName}/bestPlayer")
  public ResponseEntity<Object> getGoals(@PathVariable String teamName) {

    PlayerDTO player = playerMapper.toDTO(getBestTeamPlayerUseCase.getBestTeamPlayer(teamName));
    if (player != null) {
      return ResponseEntity.ok(player);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
