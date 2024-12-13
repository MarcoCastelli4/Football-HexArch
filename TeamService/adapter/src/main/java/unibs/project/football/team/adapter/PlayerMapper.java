package unibs.project.football.team.adapter;

import org.springframework.stereotype.Component;
import unibs.project.football.team.player.Player;

@Component
public class PlayerMapper {

  public Player toEntity(PlayerDTO playerDTO) {
    if (playerDTO == null) {
      return null; // Return null if the DTO is null
    }

    return new Player(
            playerDTO.getTeam(),
            playerDTO.getName(),
            playerDTO.getRole(),
            playerDTO.getGoal(),
            playerDTO.getAge(),
            playerDTO.getGender(),
            playerDTO.getHeight(),
            playerDTO.getWeight(),
            playerDTO.getInjuries(),
            playerDTO.getOldTeams());
  }

  // Converts from Player to PlayerDTO
  public PlayerDTO toDTO(Player player) {
    if (player == null) {
      return null; // Return null if the player is null
    }

    return new PlayerDTO(
            player.getTeam(),
            player.getName(),
            player.getRole(),
            player.getGoal(),
            player.getAge(),
            player.getGender(),
            player.getHeight(),
            player.getWeight(),
            player.getInjuries(),
            player.getOldTeams());
  }
}
