package unibs.project.football.team;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import unibs.project.football.team.port.in.team.GetBestPlayerForTeamUseCase;
import unibs.project.football.team.port.in.team.InsertPlayerToTeamUseCase;
import unibs.project.football.team.port.out.AddPlayer;
import unibs.project.football.team.port.out.OutdoorToPlayerService;
import unibs.project.football.team.port.out.persistence.TeamRepository;
import unibs.project.football.team.service.Team.GetBestPlayerForTeamService;
import unibs.project.football.team.service.Team.InsertPlayerToTeamService;

@SpringBootApplication
public class SpringAppConfig {

  @Bean
  GetBestPlayerForTeamUseCase getBestPlayerForTeamUseCase(
          OutdoorToPlayerService bestTeamPlayer, TeamRepository teamRepository) {
    return new GetBestPlayerForTeamService(bestTeamPlayer, teamRepository);
  }

  @Bean
  InsertPlayerToTeamUseCase insertPlayerToTeamUseCase(
      TeamRepository teamRepository, AddPlayer addPlayer) {
    return new InsertPlayerToTeamService(teamRepository, addPlayer);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
