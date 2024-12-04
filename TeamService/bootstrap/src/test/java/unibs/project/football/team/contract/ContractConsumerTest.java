package unibs.project.football.team.contract;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import unibs.project.football.team.adapter.out.OutdoorAddPlayerToTeam;
import unibs.project.football.team.adapter.out.OutdoorToPlayerService;
import unibs.project.football.team.player.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@TestPropertySource(properties = { "unibs.project.football.team.event=false"})
public class ContractConsumerTest {

  @Rule
  public StubRunnerRule rule = new StubRunnerRule().downloadStub(
                  "unibs.project.football.player",
                  "bootstrap")
          .withPort(8090).failOnNoStubs(true);

  @Autowired private MockMvc mockMvc;

  @Autowired private OutdoorToPlayerService outdoorToPlayerService;
  @Test
  public void testGetBestTeamPlayer() {

    Player player = outdoorToPlayerService.getBestPlayer("Juventus");

    Map<Integer, String> injuries1 = new HashMap<>();
    injuries1.put(2010, "Knee injury");
    injuries1.put(2020, "Hamstring injury");

    Map<Integer, String> oldTeams1 = new HashMap<>();
    oldTeams1.put(2010, "Real Madrid");
    oldTeams1.put(2007, "Manchester United");

    Player expected =
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

    assertEquals(player, expected);

  }

  @Autowired private OutdoorAddPlayerToTeam outdoorAddPlayerToTeam;
  @Test
  public void testAddPlayerToTeam() {

    Map<Integer, String> injuries1 = new HashMap<>();
    injuries1.put(2016, "Ankle injury");
    injuries1.put(2017, "Ankle injury");
    injuries1.put(2018, "Hamstring injury");
    injuries1.put(2022, "Ankle injury");
    injuries1.put(2023, "Ankle injury");
    injuries1.put(2024, "Hamstring injury");

    Map<Integer, String> oldTeams1 = new HashMap<>();
    oldTeams1.put(2016, "Darfo Boario");
    oldTeams1.put(2018, "Us Breno");

    Player player =
            new Player(
                    "Juventus", "Marco Castelli", "Defender", 35, 24, "Male", 183, 70, injuries1, oldTeams1);

    assertEquals(outdoorAddPlayerToTeam.addPlayer(player), "Player added successfully");

  }



}
