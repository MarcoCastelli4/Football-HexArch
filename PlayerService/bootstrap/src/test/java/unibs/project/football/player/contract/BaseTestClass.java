package unibs.project.football.player.contract;

import static unibs.project.football.player.adapter.out.persistence.DemoPlayer.getPlayer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;
import unibs.project.football.player.Launcher;
import unibs.project.football.player.adapter.in.rest.player.GetBestTeamPlayerAdapter;
import unibs.project.football.player.adapter.in.rest.player.GetPlayerForTeamAdapter;
import unibs.project.football.player.player.GetBestTeamPlayerService;
import unibs.project.football.player.player.GetPlayersService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Launcher.class)
@DirtiesContext
@AutoConfigureMessageVerifier
public abstract class BaseTestClass {

  @Autowired private WebApplicationContext webApplicationContext;
  @Autowired private GetPlayerForTeamAdapter getPlayerForTeamAdapter;
  @Autowired private GetBestTeamPlayerAdapter getBestTeamPlayerAdapter;

  @MockBean GetPlayersService getPlayersService;

  @MockBean GetBestTeamPlayerService getBestTeamPlayerService;

  @BeforeEach
  public void setup() {
    RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

    RestAssuredMockMvc.standaloneSetup(getPlayerForTeamAdapter, getBestTeamPlayerAdapter);
    Mockito.when(getPlayersService.getPlayers("Juventus"))
        .thenReturn(List.of(getPlayer("Cristiano Ronaldo"), getPlayer("Leonardo Bonucci")));

    Mockito.when(getBestTeamPlayerService.getBestTeamPlayer("Juventus"))
        .thenReturn(getPlayer("Cristiano Ronaldo"));
  }
}
