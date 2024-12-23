package unibs.project.football.player.contract;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static unibs.project.football.player.adapter.out.persistence.DemoPlayer.getPlayer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
        .thenReturn(getPlayer("Cristiano Ronaldo"));

    Mockito.when(addPlayerUseCase.addPlayer(any())).thenReturn(true);
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
