package unibs.project.football.application.port.in.team;

/**
 * An exception indicating that no product was found for the product ID specified by a customer.
 *
 * @author Sven Woltmann
 */
public class TeamAlreadyExistException extends Exception {

  public TeamAlreadyExistException(String s) {}
}
