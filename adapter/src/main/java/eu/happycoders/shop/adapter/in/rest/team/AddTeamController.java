package eu.happycoders.shop.adapter.in.rest.team;

import eu.happycoders.shop.application.port.in.team.AddPlayerToTeamUseCase;
import eu.happycoders.shop.application.port.in.team.AddTeamUseCase;
import eu.happycoders.shop.application.port.in.team.TeamAlreadyExistException;
import eu.happycoders.shop.application.port.in.team.TeamtNotFoundException;
import eu.happycoders.shop.model.player.Player;
import eu.happycoders.shop.model.team.Team;
import eu.happycoders.shop.model.team.TeamHasMaxNumberOfPlayer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static eu.happycoders.shop.adapter.in.rest.common.ControllerCommons.clientErrorException;

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
            throw clientErrorException(
                    Response.Status.BAD_REQUEST, e.toString());
        }

        return "Team added successfully!";
    }

}