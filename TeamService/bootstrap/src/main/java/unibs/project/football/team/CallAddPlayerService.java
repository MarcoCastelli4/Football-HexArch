package unibs.project.football.team;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import unibs.project.football.team.adapter.out.OutdoorAddPlayerToTeam;
import unibs.project.football.team.player.Injurie;
import unibs.project.football.team.player.OldTeam;
import unibs.project.football.team.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Configuration
class CallAddPlayerServiceConfig {

  @Bean
  @ConditionalOnProperty(value = "unibs.project.football.team.event", havingValue = "true", matchIfMissing = true)
  public CallAddPlayerService callAddPlayerService(OutdoorAddPlayerToTeam outdoorAddPlayerToTeam) {
      return new CallAddPlayerService(outdoorAddPlayerToTeam);
  }
}

class CallAddPlayerService {

  public final OutdoorAddPlayerToTeam outdoorAddPlayerToTeam;

  public CallAddPlayerService(OutdoorAddPlayerToTeam outdoorAddPlayerToTeam) {
    this.outdoorAddPlayerToTeam = outdoorAddPlayerToTeam;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void afterApplicationStart() {
    System.out.println("Spring application started");

    ArrayList<Injurie> injuries1 = new ArrayList<>();
    injuries1.add(new Injurie(2016, "Ankle injury"));
    injuries1.add(new Injurie(2017, "Ankle injury"));
    injuries1.add(new Injurie(2018, "Hamstring injury"));


    ArrayList<OldTeam> oldTeams1 = new ArrayList<>();
    oldTeams1.add(new OldTeam(2010, "Darfo Boario"));
    oldTeams1.add(new OldTeam(2007, "Us Breno"));

    Player player =
            new Player(
                    "Juventus", "Marco Castelli", "Defender", 35, 24, "Male", 183, 70, injuries1, oldTeams1);

    System.out.println("Let's consume the PLAYER service");

    outdoorAddPlayerToTeam.addPlayer(player);
  }
}