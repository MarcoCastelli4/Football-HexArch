package unibs.project.football.adapter.in.rest.team;

import static unibs.project.football.adapter.in.rest.common.ControllerCommons.clientErrorException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import unibs.project.football.application.port.in.team.AddTeamUseCase;
import unibs.project.football.application.port.in.team.TeamAlreadyExistException;
import unibs.project.football.model.team.Team;

@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
public class AddTeamController {

  private final AddTeamUseCase addTeamUseCase;

  public AddTeamController(AddTeamUseCase addTeamUseCase) {
    this.addTeamUseCase = addTeamUseCase;
  }

  @POST
  @Path("/add/line-items")
  public String addTeam(@QueryParam("teamName") String teamName) {

    try {
      // Use the appropriate method to add the team
      Team team = addTeamUseCase.AddTeam(teamName);
    } catch (TeamAlreadyExistException e) {
      // Throw a client error if the team already exists
      throw clientErrorException(Response.Status.BAD_REQUEST, e.toString());
    }

    return "Team added successfully!";
  }
}
