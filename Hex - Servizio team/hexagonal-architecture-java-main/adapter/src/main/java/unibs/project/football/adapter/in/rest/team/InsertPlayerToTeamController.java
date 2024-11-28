package unibs.project.football.adapter.in.rest.team;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unibs.project.football.adapter.PlayerDTO;
import unibs.project.football.adapter.PlayerMapper;
import unibs.project.football.application.port.in.team.InsertPlayerToTeamUseCase;
import unibs.project.football.application.port.out.persistence.TeamRepository;
import unibs.project.football.model.team.Team;

@RestController
@RequestMapping("/teams")
public class InsertPlayerToTeamController {

    private final InsertPlayerToTeamUseCase insertPlayerToTeamUseCase;
    private final PlayerMapper  playerMapper;

    public InsertPlayerToTeamController(InsertPlayerToTeamUseCase insertPlayerToTeamUseCase,PlayerMapper playerMapper) {
        this.insertPlayerToTeamUseCase = insertPlayerToTeamUseCase;
        this.playerMapper = playerMapper;
    }

    @PutMapping("/addPlayer")
    public ResponseEntity<String> addPlayerToTeam(@RequestBody PlayerDTO playerDTO) {
        try {
            insertPlayerToTeamUseCase.insertPlayerToTeam(playerDTO.getTeam(),playerMapper.toEntity(playerDTO));

            return ResponseEntity.ok("Player added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Player already exist");
        }
    }
}
