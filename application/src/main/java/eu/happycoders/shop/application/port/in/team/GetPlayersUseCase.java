package eu.happycoders.shop.application.port.in.team;
import eu.happycoders.shop.model.player.Player;

import java.util.List;

public interface GetPlayersUseCase {

    List<Player> getPlayers(String name) throws TeamtNotFoundException;
}
