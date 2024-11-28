package in.rest.team;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.when;

import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import unibs.project.football.SpringAppConfig;
import unibs.project.football.application.port.in.team.AddPlayerToTeamUseCase;
import unibs.project.football.application.port.in.team.AddTeamUseCase;
import unibs.project.football.application.port.in.team.GetPlayersUseCase;
import unibs.project.football.application.port.in.team.TeamtNotFoundException;
import unibs.project.football.model.player.Player;
import unibs.project.football.model.team.Team;
import unibs.project.football.model.team.TeamHasMaxNumberOfPlayer;

@ActiveProfiles("test")

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = SpringAppConfig.class)
public class TeamControllerTest {

  private static final String TEST_TEAMNAME = "Juventus";

  @LocalServerPort private Integer port;

  @MockBean AddPlayerToTeamUseCase addPlayerToTeamUseCase;

  @MockBean AddTeamUseCase addTeamUseCase;

  @MockBean GetPlayersUseCase getPlayersUseCase;

  @Test
  void give_TeamPlayers()
      throws TeamHasMaxNumberOfPlayer, TeamtNotFoundException {
    String name = TEST_TEAMNAME;

    List<Player> players = new ArrayList<>();
    players.add(new Player("Leonardo Bonucci", "defender"));
    players.add(new Player("Giorgio Chiellini", "defender"));
    players.add(new Player("Cristiano Ronaldo", "striker"));

    when(getPlayersUseCase.getPlayers(name)).thenReturn(players);

    Response response =
        given().port(port).get("/teams/" + name + "/players").then().extract().response();

    Assertions.assertEquals(200, response.getStatusCode()); // Check HTTP status
    Assertions.assertEquals(players.toString(),response.getBody().asString());
  }
}
