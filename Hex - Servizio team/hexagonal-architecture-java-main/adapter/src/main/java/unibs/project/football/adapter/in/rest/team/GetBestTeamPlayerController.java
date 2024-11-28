package unibs.project.football.adapter.in.rest.team;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import unibs.project.football.application.port.in.team.GetBestPlayerForTeamUseCase;
import unibs.project.football.model.player.Player;

import java.util.List;

@RestController
public class GetBestTeamPlayerController {

  private final GetBestPlayerForTeamUseCase getBestPlayerForTeamUseCase;

  public GetBestTeamPlayerController(GetBestPlayerForTeamUseCase getBestPlayerForTeamUseCase) {
    this.getBestPlayerForTeamUseCase = getBestPlayerForTeamUseCase;
  }

  @GetMapping("/bestPlayerForTeam")
  public ResponseEntity<Object> getBestPlayer() {
      List<Player>  players= getBestPlayerForTeamUseCase.getBestPlayerForTeamUseCase();
      if(players!=null) {
          return ResponseEntity.ok(players);
      }
      return ResponseEntity.badRequest().body("Empty team");
  }
}
