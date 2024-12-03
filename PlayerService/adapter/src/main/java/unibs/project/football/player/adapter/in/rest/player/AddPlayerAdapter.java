package unibs.project.football.player.adapter.in.rest.player;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unibs.project.football.player.adapter.out.persistence.PlayerDTO;
import unibs.project.football.player.adapter.out.persistence.PlayerMapper;
import unibs.project.football.player.port.in.player.AddPlayerUseCase;

@RestController
@RequestMapping("/player")
public class AddPlayerAdapter {

  private final AddPlayerUseCase addPlayerUseCase;
  private final PlayerMapper playerMapper;

  public AddPlayerAdapter(AddPlayerUseCase addPlayerUseCase, PlayerMapper playerMapper) {
    this.addPlayerUseCase = addPlayerUseCase;
    this.playerMapper = playerMapper;
  }

  @PutMapping("/addPlayer")
  public ResponseEntity<String> addPlayerToTeam(@RequestBody PlayerDTO playerDTO) {

    if (addPlayerUseCase.addPlayer(playerMapper.toEntity(playerDTO))) {
      return ResponseEntity.ok("Player added successfully");
    } else {
      return ResponseEntity.badRequest().body("Player not added");
    }
  }
}
