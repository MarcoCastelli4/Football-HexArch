package unibs.project.football.team.contract;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import unibs.project.football.team.adapter.out.OutdoorAddPlayerToTeam;
import unibs.project.football.team.adapter.out.OutdoorToPlayerService;
import unibs.project.football.team.player.Injurie;
import unibs.project.football.team.player.OldTeam;
import unibs.project.football.team.player.Player;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureJsonTesters
@TestPropertySource(properties = {"unibs.project.football.team.event=false"})
public class ContractConsumerTest {

  @ClassRule
  public static StubRunnerRule rule =
      new StubRunnerRule()
          .downloadStub("unibs.project.football.player", "bootstrap")
          .withPort(8090)
          .failOnNoStubs(true);

  @Autowired OutdoorToPlayerService outdoorToPlayerService;

  @Autowired OutdoorAddPlayerToTeam outdoorAddPlayerToTeam;

  @Test
  public void testGetBestTeamPlayer() {

    ResponseEntity<Player> response=outdoorToPlayerService.getBestPlayer("someTeam");

    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    Assertions.assertThat(response.getBody().getName()).matches("Test");
    Assertions.assertThat(response.getBody().getTeam()).matches("Test");
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
            "Juventus",
            "Marco Castelli",
            "Defender",
            35,
            24,
            "Male",
            183,
            70,
            injuries1,
            oldTeams1);

    ResponseEntity<String> response=outdoorAddPlayerToTeam.addPlayer(player);
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    Assertions.assertThat(response.getBody()).isEqualTo("Player added successfully");

  }
}
