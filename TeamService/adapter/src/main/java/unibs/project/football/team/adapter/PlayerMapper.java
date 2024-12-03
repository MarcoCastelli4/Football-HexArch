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

  // Convert Player to PlayerDTO
  public static PlayerDTO toDTO(Player player) {
    PlayerDTO playerDTO = new PlayerDTO();
    playerDTO.setTeam(player.getTeam());
    playerDTO.setName(player.getName());
    playerDTO.setRole(player.getRole());
    playerDTO.setGoal(player.getGoal());
    playerDTO.setAge(player.getAge());
    playerDTO.setGender(player.getGender());
    playerDTO.setHeight(player.getHeight());
    playerDTO.setWeight(player.getWeight());
    playerDTO.setInjuries(player.getInjuries());
    playerDTO.setOldTeams(player.getOldTeams());
    return playerDTO;
  }
}
