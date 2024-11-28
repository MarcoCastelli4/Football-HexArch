package unibs.project.football.adapter.in.rest.player;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unibs.project.football.adapter.out.persistence.PlayerDTO;
import unibs.project.football.adapter.out.persistence.PlayerMapper;
import unibs.project.football.application.port.in.player.AddPlayerUseCase;
import unibs.project.football.application.port.in.player.GetBestTeamPlayerUseCase;

@RestController
public class AddPlayerAdapter {

    private final AddPlayerUseCase addPlayerUseCase;
    private final PlayerMapper playerMapper;

    public AddPlayerAdapter(AddPlayerUseCase addPlayerUseCase, PlayerMapper playerMapper) {
        this.addPlayerUseCase = addPlayerUseCase;
        this.playerMapper = playerMapper;
    }

    @PutMapping("/players")
    public ResponseEntity<String> addPlayerToTeam(@RequestBody PlayerDTO playerDTO) {

        if(addPlayerUseCase.addPlayer(playerMapper.toEntity(playerDTO))){
            return ResponseEntity.ok("Player added successfully");
        }
        else {
            return ResponseEntity.badRequest().body("Player not added");
        }


    }
}
