package unibs.project.football.player.adapter.in.rest.player;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unibs.project.football.player.adapter.out.persistence.PlayerDTO;
import unibs.project.football.player.adapter.out.persistence.PlayerMapper;
import unibs.project.football.player.port.in.player.GetPlayersUseCase;

@RestController
@RequestMapping("/player")
public class GetPlayerForTeamAdapter {

  private final GetPlayersUseCase getPlayersUseCase;
  private final PlayerMapper playerMapper;

  public GetPlayerForTeamAdapter(GetPlayersUseCase getPlayersUseCase, PlayerMapper playerMapper) {
    this.getPlayersUseCase = getPlayersUseCase;
    this.playerMapper = playerMapper;
  }

  @GetMapping("/{teamName}/all")
  public ResponseEntity<Object> getPlayers(@PathVariable("teamName") String teamName) {
    List<PlayerDTO> players =
        getPlayersUseCase.getPlayers(teamName).stream()
            .map(playerMapper::toDTO)
            .collect(Collectors.toList());

    if (players.isEmpty()) {
      return ResponseEntity.badRequest().body("Team empty!");
    }
    return ResponseEntity.ok(players);
  }
}
