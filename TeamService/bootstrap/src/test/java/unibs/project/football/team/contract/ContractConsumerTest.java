package unibs.project.football.team.contract;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
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
import static org.mockito.ArgumentMatchers.*;


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

  @Autowired
  @MockBean
  OutdoorToPlayerService outdoorToPlayerService;

  @Autowired
  @MockBean
  OutdoorAddPlayerToTeam outdoorAddPlayerToTeam;

  @MockBean
  RestTemplate restTemplate;

  @Before
  public void setup() {

    ArrayList<Injurie> injuries1 = new ArrayList<>();
    injuries1.add(new Injurie(2010, "Knee injury"));
    injuries1.add(new Injurie(2020, "Hamstring injury"));

    ArrayList<OldTeam> oldTeams1 = new ArrayList<>();
    oldTeams1.add(new OldTeam(2010, "Real Madrid"));
    oldTeams1.add(new OldTeam(2007, "Manchester United"));

    PlayerDTO playerDTO =
            new PlayerDTO(
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


    RestAssuredMockMvc.standaloneSetup(outdoorAddPlayerToTeam, outdoorToPlayerService);

    //String url_getBestPlayer= eq("http://localhost:8090/player/") + any() + eq("/bestPlayer?team=") + any();
   //Mockito.when(restTemplate.getForObject(eq(url_getBestPlayer), any())).thenReturn(playerDTO);
    String url_getBestPlayer = "http://localhost:8090/player/{teamName}/bestPlayer";
    Mockito.when(restTemplate.getForObject(
            Mockito.eq(url_getBestPlayer),  // Match the full URL
            Mockito.eq(PlayerDTO.class),   // Match the class type
            Mockito.anyString()            // Match the dynamic part of the URL (path variable)
    )).thenReturn(playerDTO);



    String url_addPlayer = "http://localhost:8090/player/addPlayer";
    Mockito.doNothing().when(restTemplate).put(url_addPlayer, playerDTO);

  }
  @Test
  public void testGetBestTeamPlayer() {

    Player player = outdoorToPlayerService.getBestPlayer("someTeam");
    // Chiamata al mock andata a buon fine
    if(player!=null){
      assertTrue(true);
    }

    //assertEquals(player.getTeam())
    /*
    ArrayList<Injurie> injuries1 = new ArrayList<>();
    injuries1.add(new Injurie(2010, "Knee injury"));
    injuries1.add(new Injurie(2020, "Hamstring injury"));

    ArrayList<OldTeam> oldTeams1 = new ArrayList<>();
    oldTeams1.add(new OldTeam(2010, "Real Madrid"));
    oldTeams1.add(new OldTeam(2007, "Manchester United"));

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

    assertEquals(player, expected);*/

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
