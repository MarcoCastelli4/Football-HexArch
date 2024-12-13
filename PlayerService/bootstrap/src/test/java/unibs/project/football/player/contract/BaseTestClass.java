package unibs.project.football.player.contract;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static unibs.project.football.player.adapter.out.persistence.DemoPlayer.getPlayer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.context.WebApplicationContext;
import unibs.project.football.player.*;
import unibs.project.football.player.adapter.in.rest.player.AddPlayerAdapter;
import unibs.project.football.player.adapter.in.rest.player.GetBestTeamPlayerAdapter;
import unibs.project.football.player.port.in.player.AddPlayerUseCase;
import unibs.project.football.player.port.in.player.GetBestTeamPlayerUseCase;

@SpringBootTest(classes = Launcher.class)
public abstract class BaseTestClass {

  @Autowired private GetBestTeamPlayerAdapter getBestTeamPlayerAdapter;
  @Autowired private AddPlayerAdapter addPlayerAdapter;
  @Autowired private WebApplicationContext webApplicationContext;

  // @MockBean AddPlayerService addPlayerService;
  // @MockBean GetBestTeamPlayerService getBestTeamPlayerService;

  @MockBean AddPlayerUseCase addPlayerUseCase;

  @MockBean GetBestTeamPlayerUseCase getBestTeamPlayerUseCase;

  @BeforeEach
  public void setup() {
    // RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

    // mokkiamo i servizi che in base alla richiesta restituiscono subito i dati senza cercare nel
    // dbk
    RestAssuredMockMvc.standaloneSetup(getBestTeamPlayerAdapter, addPlayerAdapter);

    // Simula il comportamento dello use case usando thenAnswer
    Mockito.when(getBestTeamPlayerUseCase.getBestTeamPlayer(anyString()))
        .thenAnswer(
            new Answer<Player>() {
              @Override
              public Player answer(InvocationOnMock invocation) throws Throwable {
                System.out.println("GetBestTeamPlayerUseCase::getBestTeamPlayer mocked");

                return getPlayer("Cristiano Ronaldo");
              }
            });

    Mockito.when(getBestTeamPlayerUseCase.getBestTeamPlayer(anyString()))
        .thenReturn(getPlayer("Cristiano Ronaldo"));

    ArrayList<Injurie> injuries1 = new ArrayList<>();
    injuries1.add(new Injurie(2011, "Knee injury"));
    injuries1.add(new Injurie(2012, "Hamstring injury"));

    ArrayList<OldTeam> oldTeams1 = new ArrayList<>();
    oldTeams1.add(new OldTeam(2015, "Real Madrid"));
    oldTeams1.add(new OldTeam(2020, "Juventus"));
    oldTeams1.add(new OldTeam(2022, "Atletico Madrid"));

    //    Player player =
    //        new Player(
    //            "Juventus",
    //            "David Trezeguet",
    //            "Forward",
    //            135,
    //            26,
    //            "Male",
    //            187,
    //            83,
    //            injuries1,
    //            oldTeams1);

    Mockito.when(addPlayerUseCase.addPlayer(any())).thenReturn(true);
    // FARE ESEMPI PER FAR FALLIRE CONTRATTO

  }

  @Test
  public void TestErrato() {
    //  Assert.assertEquals("Ronaldo", getPlayersService.getPlayers("Juventus").getFirst());
  }

  @Test
  public void TEstGiusto() {
    /*
    Prendo TestRestTemplate
    ibvoco una API di Player tipo
       CURL -X GET /getPlayer/Juventus/all
       assert -> http response = 200
       assert -> body contains Ronaldo
    */
  }
}
