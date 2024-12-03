package unibs.project.football.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import unibs.project.football.player.player.AddPlayerService;
import unibs.project.football.player.player.GetBestTeamPlayerService;
import unibs.project.football.player.player.GetPlayersService;
import unibs.project.football.player.port.in.player.AddPlayerUseCase;
import unibs.project.football.player.port.in.player.GetBestTeamPlayerUseCase;
import unibs.project.football.player.port.in.player.GetPlayersUseCase;
import unibs.project.football.player.port.out.persistence.PlayerRepository;

@SpringBootApplication
public class SpringAppConfig {

  @Autowired PlayerRepository playerRepository;

  @Bean
  GetBestTeamPlayerUseCase getBestTeamPlayerUseCase() {
    return new GetBestTeamPlayerService(playerRepository);
  }

  @Bean
  AddPlayerUseCase addPlayerUseCase() {
    return new AddPlayerService(playerRepository);
  }

  @Bean
  GetPlayersUseCase getPlayersUseCase() {
    return new GetPlayersService(playerRepository);
  }
}
