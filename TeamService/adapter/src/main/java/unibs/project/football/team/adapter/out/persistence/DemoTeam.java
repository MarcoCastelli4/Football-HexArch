package unibs.project.football.team.adapter.out.persistence;

import java.util.List;
import unibs.project.football.team.team.Team;

/** Demo teams and their players that are automatically stored in the team database. */
public final class DemoTeam {

  public static final Team Juventus = new Team("Juventus");
  public static final Team Milan = new Team("Milan");
  public static final Team Inter = new Team("Inter");

  // List of demo teams
  public static final List<Team> DEMO_TEAMS = List.of(Juventus, Milan, Inter);

  private DemoTeam() {}
}
