package eu.happycoders.shop.application.port.in.team;

import eu.happycoders.shop.model.player.Player;
import eu.happycoders.shop.model.team.Team;
import eu.happycoders.shop.model.team.TeamHasMaxNumberOfPlayer;

public interface AddPlayerToTeamUseCase {

    Team addPlayerToTeam(String name, Player player)
            throws TeamtNotFoundException, TeamHasMaxNumberOfPlayer;
}
