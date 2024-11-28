package unibs.project.football;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;
import unibs.project.football.application.port.in.message.MessageUseCase;
import unibs.project.football.application.port.in.team.GetBestPlayerForTeamUseCase;
import unibs.project.football.application.port.in.team.InsertPlayerToTeamUseCase;
import unibs.project.football.application.port.out.AddPlayer;
import unibs.project.football.application.port.out.FindBestPlayerForTeam;
import unibs.project.football.application.port.out.persistence.MessageRepository;
import unibs.project.football.application.port.out.persistence.TeamRepository;
import unibs.project.football.application.service.Team.GetBestPlayerForTeamService;
import unibs.project.football.application.service.Team.InsertPlayerToTeamService;
import unibs.project.football.application.service.message.messageService;

@SpringBootApplication
public class SpringAppConfig {


  @Autowired MessageRepository messageRepository;


  @Bean
  GetBestPlayerForTeamUseCase getBestPlayerForTeamUseCase(FindBestPlayerForTeam bestTeamPlayer, TeamRepository teamRepository) {
    return new GetBestPlayerForTeamService(bestTeamPlayer,teamRepository);
  }

  @Bean
  InsertPlayerToTeamUseCase insertPlayerToTeamUseCase(TeamRepository teamRepository, AddPlayer addPlayer) {
    return new InsertPlayerToTeamService(teamRepository, addPlayer);
  }

  @Bean
  MessageUseCase messageUseCase() {
    return new messageService(messageRepository);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
