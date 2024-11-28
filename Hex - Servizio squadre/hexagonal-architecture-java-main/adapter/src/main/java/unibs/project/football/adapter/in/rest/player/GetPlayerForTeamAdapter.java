package unibs.project.football.adapter.in.rest.player;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.websocket.server.PathParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import unibs.project.football.adapter.out.persistence.PlayerDTO;
import unibs.project.football.adapter.out.persistence.PlayerMapper;
import unibs.project.football.application.port.in.player.GetPlayersUseCase;
import unibs.project.football.model.player.Player;

@RestController
public class GetPlayerForTeamAdapter {

  private final GetPlayersUseCase getPlayersUseCase;
  private final PlayerMapper playerMapper;

  public GetPlayerForTeamAdapter(GetPlayersUseCase getPlayersUseCase,PlayerMapper playerMapper) {
    this.getPlayersUseCase = getPlayersUseCase;
    this.playerMapper = playerMapper;
  }

  @GetMapping("/{teamName}/players")
  public ResponseEntity<Object> getPlayers(@PathVariable("teamName") String teamName) {
    List< PlayerDTO> players=getPlayersUseCase.getPlayers(teamName).stream().map(playerMapper::toDTO).collect(Collectors.toList());

    if(players.isEmpty()){
      return ResponseEntity.badRequest().body("Team empty!");
    }
    return ResponseEntity.ok(players);

  }
}