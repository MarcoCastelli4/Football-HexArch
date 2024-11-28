package unibs.project.football.application.service.player;

import unibs.project.football.application.port.in.player.GetPlayersUseCase;
import unibs.project.football.application.port.out.persistence.PlayerRepository;
import unibs.project.football.model.player.Player;

import java.util.List;
import java.util.Objects;


public class GetPlayersService implements GetPlayersUseCase {

  private final PlayerRepository playerRepository;

  public GetPlayersService(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public List<Player> getPlayers(String teamName){
    Objects.requireNonNull(teamName, "'team name' must not be null");
    return playerRepository.getPlayerForTeam(teamName);
  }
}