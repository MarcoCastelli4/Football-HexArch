package unibs.project.football.player.contract;

import static unibs.project.football.player.adapter.out.persistence.DemoPlayer.getPlayer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import unibs.project.football.player.Player;
import unibs.project.football.player.adapter.in.rest.player.AddPlayerAdapter;
import unibs.project.football.player.adapter.in.rest.player.GetBestTeamPlayerAdapter;
import unibs.project.football.player.adapter.in.rest.player.GetPlayerForTeamAdapter;
import unibs.project.football.player.player.AddPlayerService;
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
  @Autowired private AddPlayerAdapter addPlayerAdapter;

  @MockBean GetPlayersService getPlayersService;
  @MockBean AddPlayerService addPlayerService;
  @MockBean GetBestTeamPlayerService getBestTeamPlayerService;

  @BeforeEach
  public void setup() {
    RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

    // mokkiamo i servizi che in base alla richiesta restituiscono subito i dati senza cercare nel
    // db
    RestAssuredMockMvc.standaloneSetup(
        getPlayerForTeamAdapter, getBestTeamPlayerAdapter, addPlayerAdapter);
    Mockito.when(getPlayersService.getPlayers("Juventus"))
        .thenReturn(List.of(getPlayer("Cristiano Ronaldo"), getPlayer("Leonardo Bonucci")));

    Mockito.when(getBestTeamPlayerService.getBestTeamPlayer("Juventus"))
        .thenReturn(getPlayer("Cristiano Ronaldo"));

    Map<Integer, String> injuries1 = new HashMap<>();
    injuries1.put(2010, "Knee injury");
    injuries1.put(2020, "Hamstring injury");

    Map<Integer, String> oldTeams1 = new HashMap<>();
    oldTeams1.put(2010, "Real Madrid");
    oldTeams1.put(2007, "Manchester United");

    Player player =
        new Player(
            "Juventus", "Bobo Vieri", "Striker", 700, 36, "Male", 187, 83, injuries1, oldTeams1);

    Mockito.when(addPlayerService.addPlayer(player)).thenReturn(true);
  }
}
