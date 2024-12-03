package unibs.project.football.team.contract;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import unibs.project.football.team.adapter.out.AddPlayerToTeamController;
import unibs.project.football.team.player.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(stubsMode = LOCAL, ids = "unibs.project.football.player:bootstrap:+:8090")
public class CDCPlayerService {

  @Autowired
  AddPlayerToTeamController door;

  @Autowired private MockMvc mockMvc;

  @Test
  public void outDoor() {
    //   door.addPlayer();
  }

  // USO MOCK
  @Test
  public void testGetPlayersWithMockMvc() throws Exception {
    mockMvc
            .perform(
                    MockMvcRequestBuilders.get("/player/Juventus/all")
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }


  @Test
  public void GetPlayerForTeam() {
    RestTemplate restTemplate = new RestTemplate();

    // Call the stubbed endpoint

    ResponseEntity<Player[]> response =
        restTemplate.getForEntity(
            "http://localhost:8090/player/Juventus/all?team=Juventus", Player[].class);

    // Assert the response status
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    // Extract the response body
    List<Player> players = Arrays.asList(response.getBody());

    // Define expected players
    Map<Integer, String> injuries1 = new HashMap<>();
    injuries1.put(2010, "Knee injury");
    injuries1.put(2020, "Hamstring injury");

    Map<Integer, String> oldTeams1 = new HashMap<>();
    oldTeams1.put(2010, "Real Madrid");
    oldTeams1.put(2007, "Manchester United");

    // Player for Juventus
    Player player1 =
        new Player(
            "Juventus",
            "Cristiano Ronaldo",
            "Striker",
            700,
            36,
            "Male",
            187,
            83,
            injuries1,
            oldTeams1);

    Map<Integer, String> injuries4 = new HashMap<>();
    injuries4.put(2014, "Shoulder injury");

    Map<Integer, String> oldTeams4 = new HashMap<>();
    oldTeams4.put(2010, "Bari");

    // Player for Juventus
    Player player2 =
        new Player(
            "Juventus",
            "Leonardo Bonucci",
            "Defender",
            50,
            36,
            "Male",
            189,
            89,
            injuries4,
            oldTeams4);

    // Assert the players
    assertTrue(players.size() == 2);
    assertTrue(players.get(0).equals(player1));
    assertTrue(players.get(1).equals(player2));
  }


  @Test
  public void GetBestTeamPlayer() {
    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<Player[]> response =
        restTemplate.getForEntity(
            "http://localhost:8090/player/Juventus/bestPlayer?team=Juventus", Player[].class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    List<Player> players = Arrays.asList(response.getBody());

    Map<Integer, String> injuries1 = new HashMap<>();
    injuries1.put(2010, "Knee injury");
    injuries1.put(2020, "Hamstring injury");

    Map<Integer, String> oldTeams1 = new HashMap<>();
    oldTeams1.put(2010, "Real Madrid");
    oldTeams1.put(2007, "Manchester United");

    Player player1 =
        new Player(
            "Juventus",
            "Cristiano Ronaldo",
            "Striker",
            700,
            36,
            "Male",
            187,
            83,
            injuries1,
            oldTeams1);


    assertTrue(players.size() == 1);
    assertTrue(players.get(0).equals(player1));
  }
}
