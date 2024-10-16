package unibs.project.football.adapter.out.persistence;

import unibs.project.football.model.player.Player;
import unibs.project.football.model.team.Team;
import java.util.List;

/** Demo teams and their players that are automatically stored in the team database. */
public final class DemoTeam {

  // Defining Juventus players
  private static final List<Player> JUVENTUS_PLAYERS =
      List.of(
          new Player("Cristiano Ronaldo", "Forward"),
          new Player("Giorgio Chiellini", "Defender"),
          new Player("Paulo Dybala", "Forward"));

  // Defining Milan players
  private static final List<Player> MILAN_PLAYERS =
      List.of(
          new Player("Zlatan Ibrahimović", "Forward"),
          new Player("Franck Kessié", "Midfielder"),
          new Player("Theo Hernández", "Defender"));

  // Defining Inter players
  private static final List<Player> INTER_PLAYERS =
      List.of(
          new Player("Romelu Lukaku", "Forward"),
          new Player("Lautaro Martínez", "Forward"),
          new Player("Milan Škriniar", "Defender"));

  // Defining Napoli players
  private static final List<Player> NAPOLI_PLAYERS =
      List.of(
          new Player("Dries Mertens", "Forward"),
          new Player("Kalidou Koulibaly", "Defender"),
          new Player("Lorenzo Insigne", "Forward"));

  // Define teams with players
  public static final Team Juventus = new Team("Juventus", JUVENTUS_PLAYERS);
  public static final Team Milan = new Team("Milan", MILAN_PLAYERS);
  public static final Team Inter = new Team("Inter", INTER_PLAYERS);
  public static final Team Napoli = new Team("Napoli", NAPOLI_PLAYERS);

  // List of demo teams
  public static final List<Team> DEMO_TEAMS = List.of(Juventus, Milan, Inter, Napoli);

  private DemoTeam() {}
}
