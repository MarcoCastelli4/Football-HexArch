package unibs.project.football.adapter.in.rest.team;

import static unibs.project.football.adapter.in.rest.common.ControllerCommons.clientErrorException;

import unibs.project.football.application.port.in.team.AddPlayerToTeamUseCase;
import unibs.project.football.application.port.in.team.TeamtNotFoundException;
import unibs.project.football.model.player.Player;
import unibs.project.football.model.team.Team;
import unibs.project.football.model.team.TeamHasMaxNumberOfPlayer;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/** REST controller for all shopping cart use cases. */
@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
public class AddToPlayerToTeamController {

  private final AddPlayerToTeamUseCase addPlayerToTeamUseCase;

  public AddToPlayerToTeamController(AddPlayerToTeamUseCase addPlayerToTeamUseCase) {
    this.addPlayerToTeamUseCase = addPlayerToTeamUseCase;
  }

  @POST
  @Path("/{teamName}/line-items")
  public String addPlayer(
      @PathParam("teamName") String teamName,
      @QueryParam("playerName") String playerName,
      @QueryParam("role") String role) {

    Player player = new Player(playerName, role);

    try {
      Team team = addPlayerToTeamUseCase.addPlayerToTeam(teamName, player);
    } catch (TeamtNotFoundException | TeamHasMaxNumberOfPlayer e) {
      throw clientErrorException(Response.Status.BAD_REQUEST, e.toString());
    }
    return "Player added successfully!";
  }
}
