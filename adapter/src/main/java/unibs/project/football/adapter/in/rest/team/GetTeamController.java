package unibs.project.football.adapter.in.rest.team;

import static unibs.project.football.adapter.in.rest.common.ControllerCommons.clientErrorException;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import unibs.project.football.application.port.in.team.GetPlayersUseCase;
import unibs.project.football.application.port.in.team.TeamtNotFoundException;
import unibs.project.football.model.player.Player;

@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
public class GetTeamController {

  private final GetPlayersUseCase getPlayersUseCase;

  public GetTeamController(GetPlayersUseCase getPlayersUseCase) {
    this.getPlayersUseCase = getPlayersUseCase;
  }

  @GET
  @Path("/{name}/players")
  @Produces(MediaType.APPLICATION_JSON) // Return JSON response
  public String getPlayers(@PathParam("name") String teamName) throws TeamtNotFoundException {
    List<Player> players;
    try {
      players = getPlayersUseCase.getPlayers(teamName);
    } catch (TeamtNotFoundException e) {
      throw clientErrorException(Response.Status.BAD_REQUEST, "The request team doesn't exist!");
    }
    return "Team: " + teamName + '\n' + players.toString();
  }
}
