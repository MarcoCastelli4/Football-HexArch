package unibs.project.football.team.contract;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import unibs.project.football.team.adapter.PlayerDTO;
import unibs.project.football.team.adapter.out.OutdoorAddPlayerToTeam;
import unibs.project.football.team.adapter.out.OutdoorToPlayerService;
import unibs.project.football.team.player.Injurie;
import unibs.project.football.team.player.OldTeam;
import unibs.project.football.team.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureJsonTesters
@TestPropertySource(properties = { "unibs.project.football.team.event=false"})
public class ContractConsumerTest {

  @ClassRule
  public static StubRunnerRule rule = new StubRunnerRule().downloadStub(
                  "unibs.project.football.player",
                  "bootstrap")
          .withPort(8090).failOnNoStubs(true);

  @Autowired
  OutdoorToPlayerService outdoorToPlayerService;

  @Autowired
  OutdoorAddPlayerToTeam outdoorAddPlayerToTeam;

  @Test
  public void testGetBestTeamPlayer() {
    assertDoesNotThrow(() -> {
      outdoorToPlayerService.getBestPlayer("someTeam");
    }
    );
  }

  @Test
  public void testAddPlayerToTeam() {

    ArrayList<Injurie> injuries1 = new ArrayList<>();
    injuries1.add(new Injurie(2016, "Ankle injury"));
    injuries1.add(new Injurie(2017, "Hamstring injury"));

    ArrayList<OldTeam> oldTeams1 = new ArrayList<>();
    oldTeams1.add(new OldTeam(2016, "Real Madrid"));
    oldTeams1.add(new OldTeam(2018, "Manchester United"));

    Player player =
            new Player(
                    "Juventus", "Marco Castelli", "Defender", 35, 24, "Male", 183, 70, injuries1, oldTeams1);

    assertEquals(outdoorAddPlayerToTeam.addPlayer(player), "Player added successfully");
  }



}
