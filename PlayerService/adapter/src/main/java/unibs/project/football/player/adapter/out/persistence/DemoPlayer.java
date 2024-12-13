package unibs.project.football.player.adapter.out.persistence;

import java.util.ArrayList;
import java.util.List;
import unibs.project.football.player.Injurie;
import unibs.project.football.player.OldTeam;
import unibs.project.football.player.Player;

/** Demo teams and their players that are automatically stored in the team database. */
public final class DemoPlayer {

  public static final List<Player> DEMO_PLAYER = new ArrayList<>();

  static {
    // Creating injuries and old teams for some players
    ArrayList<Injurie> injuries1 = new ArrayList<>();
    injuries1.add(new Injurie(2010, "Knee injury"));
    injuries1.add(new Injurie(2020, "Hamstring injury"));

    ArrayList<OldTeam> oldTeams1 = new ArrayList<>();
    oldTeams1.add(new OldTeam(2010, "Real Madrid"));
    oldTeams1.add(new OldTeam(2007, "Manchester United"));

    // Player for Juventus
    Player player1 =
        new Player(
            "Juventus",
            "Cristiano Ronaldo",
            "Forward",
            700,
            36,
            "Male",
            187,
            83,
            injuries1,
            oldTeams1);

    ArrayList<Injurie> injuries2 = new ArrayList<>();
    injuries2.add(new Injurie(2021, "Ankle sprain"));

    ArrayList<OldTeam> oldTeams2 = new ArrayList<>();
    oldTeams2.add(new OldTeam(2022, "AC Milan"));

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

    ArrayList<Injurie> injuries3 = new ArrayList<>();
    injuries3.add(new Injurie(2019, "Back problem"));

    ArrayList<OldTeam> oldTeams3 = new ArrayList<>();
    oldTeams3.add(new OldTeam(2021, "Barcelona"));

    // Player for Inter
    Player player3 =
        new Player(
            "Inter", "Romelu Lukaku", "Striker", 400, 28, "Male", 190, 94, injuries3, oldTeams3);

    ArrayList<Injurie> injuries4 = new ArrayList<>();
    injuries4.add(new Injurie(2020, "Knee injuries"));

    ArrayList<OldTeam> oldTeams4 = new ArrayList<>();
    oldTeams4.add(new OldTeam(2010, "Bari"));

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

    ArrayList<Injurie> injuries5 = new ArrayList<>();
    injuries5.add(new Injurie(2011, "Knee injury"));
    injuries5.add(new Injurie(2012, "Hamstring injury"));

    ArrayList<OldTeam> oldTeams5 = new ArrayList<>();
    oldTeams5.add(new OldTeam(2015, "Real Madrid"));
    oldTeams5.add(new OldTeam(2020, "Juventus"));
    oldTeams5.add(new OldTeam(2022, "Atletico Madrid"));

    // Player for Milan
    Player player5 =
        new Player(
            "Milan", "Alvaro Morata", "Striker", 90, 36, "Male", 187, 83, injuries5, oldTeams5);

    ArrayList<Injurie> injuries6 = new ArrayList<>();

    ArrayList<OldTeam> oldTeams6 = new ArrayList<>();
    oldTeams6.add(new OldTeam(2000, "Juventus"));

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
