package unibs.project.football;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import unibs.project.football.application.port.in.player.AddPlayerUseCase;
import unibs.project.football.application.port.in.player.GetBestTeamPlayerUseCase;
import unibs.project.football.application.port.in.player.GetPlayersUseCase;
import unibs.project.football.application.port.out.persistence.PlayerRepository;
import unibs.project.football.application.service.player.AddPlayerService;
import unibs.project.football.application.service.player.GetPlayerGoalService;
import unibs.project.football.application.service.player.GetPlayersService;


@SpringBootApplication
public class SpringAppConfig {

  @Autowired
  PlayerRepository playerRepository;

  @Bean
  GetBestTeamPlayerUseCase getPlayerGoalUseCase() {
    return new GetPlayerGoalService(playerRepository);
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
