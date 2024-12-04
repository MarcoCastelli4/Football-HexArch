package unibs.project.football.team;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import unibs.project.football.team.adapter.out.OutdoorToPlayerService;
import unibs.project.football.team.player.Player;

import java.util.HashMap;
import java.util.Map;

@Configuration
class CallPlayerServiceConfig {

  @Bean
  @ConditionalOnProperty(value = "unibs.project.football.team.event", havingValue = "true", matchIfMissing = true)
  public CallPlayerService callPlayerService(OutdoorToPlayerService findBestPlayerForTeamController) {
      return new CallPlayerService(findBestPlayerForTeamController);
  }
}

class CallPlayerService {

  public final OutdoorToPlayerService findBestPlayerForTeamController;

  public CallPlayerService(OutdoorToPlayerService findBestPlayerForTeamController) {
    this.findBestPlayerForTeamController = findBestPlayerForTeamController;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void afterApplicationStart() {
    System.out.println("Spring application started");


    System.out.println("Let's consume the PLAYER service");

    var result = findBestPlayerForTeamController.getBestPlayer("Juventus");
  }
}