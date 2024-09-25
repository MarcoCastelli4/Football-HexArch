package eu.happycoders.shop.application.port.out.persistence;

import eu.happycoders.shop.application.port.in.team.TeamtNotFoundException;
import eu.happycoders.shop.model.cart.Cart;
import eu.happycoders.shop.model.customer.CustomerId;
import eu.happycoders.shop.model.player.Player;
import eu.happycoders.shop.model.team.Team;

import java.util.List;
import java.util.Optional;

public interface TeamRepository {

    void save(Team team);
    Optional<Team> findByTeamName(String name);


}
