package unibs.project.football.team.adapter.in.rest.team;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unibs.project.football.team.player.Player;
import unibs.project.football.team.port.in.team.GetBestPlayerForTeamUseCase;

@RestController
@RequestMapping("/team")
public class GetBestTeamPlayerController {

  private final GetBestPlayerForTeamUseCase getBestPlayerForTeamUseCase;

  public GetBestTeamPlayerController(GetBestPlayerForTeamUseCase getBestPlayerForTeamUseCase) {
    this.getBestPlayerForTeamUseCase = getBestPlayerForTeamUseCase;
  }

  @GetMapping("/bestPlayers")
  public ResponseEntity<Object> getBestPlayer() {
    List<Player> players = getBestPlayerForTeamUseCase.getBestPlayerForTeamUseCase();
    if (players != null) {
      return ResponseEntity.ok(players);
    }
    return ResponseEntity.badRequest().body("Empty team");
  }
}
