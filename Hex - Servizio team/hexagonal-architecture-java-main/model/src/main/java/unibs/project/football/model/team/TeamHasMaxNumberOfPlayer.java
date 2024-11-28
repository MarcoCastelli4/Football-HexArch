package unibs.project.football.model.team;

/**
 * An exception indicating that a customer wanted to add more items of a product to the cart than
 * were available.
 *
 * @author Sven Woltmann
 */
public class TeamHasMaxNumberOfPlayer extends Exception {

  private final int playersInTeam;

  public TeamHasMaxNumberOfPlayer(String message, int playersInTeam) {
    super(message);
    this.playersInTeam = playersInTeam;
  }

  public int getPlayersInTeam() {
    return playersInTeam;
  }
}
