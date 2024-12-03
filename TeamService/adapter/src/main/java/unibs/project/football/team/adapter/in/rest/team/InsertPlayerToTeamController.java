package unibs.project.football.team.adapter.in.rest.team;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unibs.project.football.team.adapter.PlayerDTO;
import unibs.project.football.team.adapter.PlayerMapper;
import unibs.project.football.team.port.in.team.InsertPlayerToTeamUseCase;

@RestController
@RequestMapping("/team")
public class InsertPlayerToTeamController {

  private final InsertPlayerToTeamUseCase insertPlayerToTeamUseCase;
  private final PlayerMapper playerMapper;

  public InsertPlayerToTeamController(
      InsertPlayerToTeamUseCase insertPlayerToTeamUseCase, PlayerMapper playerMapper) {
    this.insertPlayerToTeamUseCase = insertPlayerToTeamUseCase;
    this.playerMapper = playerMapper;
  }

  @PutMapping("/addPlayer")
  public ResponseEntity<String> addPlayerToTeam(@RequestBody PlayerDTO playerDTO) {
    try {
      insertPlayerToTeamUseCase.insertPlayerToTeam(
          playerDTO.getTeam(), playerMapper.toEntity(playerDTO));

      return ResponseEntity.ok("Player added successfully");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Player already exist");
    }
  }
}
