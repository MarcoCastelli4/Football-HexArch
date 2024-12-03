package unibs.project.football.player.adapter.out.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import unibs.project.football.player.Player;

/** Demo teams and their players that are automatically stored in the team database. */
public final class DemoPlayer {

  public static final List<Player> DEMO_PLAYER = new ArrayList<>();

  static {
    // Creating injuries and old teams for some players
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

    Map<Integer, String> injuries2 = new HashMap<>();
    injuries2.put(2021, "Ankle sprain");

    Map<Integer, String> oldTeams2 = new HashMap<>();
    oldTeams2.put(2022, "AC Milan");

    // Player for Milan
    Player player2 =
        new Player(
            "Milan",
            "Zlatan Ibrahimovic",
            "Striker",
            550,
            39,
            "Male",
            195,
            95,
            injuries2,
            oldTeams2);

    Map<Integer, String> injuries3 = new HashMap<>();
    injuries3.put(2019, "Back problem");

    Map<Integer, String> oldTeams3 = new HashMap<>();
    oldTeams3.put(2021, "Barcelona");

    // Player for Inter
    Player player3 =
        new Player(
            "Inter", "Romelu Lukaku", "Striker", 400, 28, "Male", 190, 94, injuries3, oldTeams3);

    Map<Integer, String> injuries4 = new HashMap<>();
    injuries4.put(2020, "Knee injuries");

    Map<Integer, String> oldTeams4 = new HashMap<>();
    oldTeams4.put(2010, "Bari");

    // Player for Juventus
    Player player4 =
        new Player(
            "Juventus",
            "Leonardo Bonucci",
            "Defender",
            40,
            36,
            "Male",
            187,
            84,
            injuries4,
            oldTeams4);

    Map<Integer, String> injuries5 = new HashMap<>();
    injuries5.put(2011, "Knee injury");
    injuries5.put(2012, "Hamstring injury");

    Map<Integer, String> oldTeams5 = new HashMap<>();
    oldTeams5.put(2015, "Real Madrid");
    oldTeams5.put(2020, "Juventus");
    oldTeams5.put(2022, "Atletico Madrid");

    // Player for Milan
    Player player5 =
        new Player(
            "Milan", "Alvaro Morata", "Striker", 90, 36, "Male", 187, 83, injuries5, oldTeams5);

    Map<Integer, String> injuries6 = new HashMap<>();

    Map<Integer, String> oldTeams6 = new HashMap<>();
    oldTeams6.put(2000, "Juventus");

    // Player for Juventus
    Player player6 =
        new Player(
            "Inter", "Bobo Vieri", "Striker", 700, 36, "Male", 187, 83, injuries6, oldTeams6);

    // Adding players to the list
    DEMO_PLAYER.add(player1);
    DEMO_PLAYER.add(player2);
    DEMO_PLAYER.add(player3);
    DEMO_PLAYER.add(player4);
    DEMO_PLAYER.add(player5);
    DEMO_PLAYER.add(player6);
  }

  private DemoPlayer() {}

  public static Player getPlayer(String name) {
    for (int i = 0; i < DEMO_PLAYER.size(); i++) {
      if (DEMO_PLAYER.get(i).getName().equals(name)) {
        return DEMO_PLAYER.get(i);
      }
    }
    return null;
  }
}
