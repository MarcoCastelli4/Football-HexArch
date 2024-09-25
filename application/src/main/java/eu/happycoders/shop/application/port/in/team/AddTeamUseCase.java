package eu.happycoders.shop.application.port.in.team;
import eu.happycoders.shop.model.team.Team;
import eu.happycoders.shop.model.team.TeamHasMaxNumberOfPlayer;;

public interface AddTeamUseCase {
    Team AddTeam(String name)
            throws TeamAlreadyExistException;
}
