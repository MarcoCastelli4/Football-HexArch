package eu.happycoders.shop.application.service.Team;

import eu.happycoders.shop.application.port.in.cart.ProductNotFoundException;
import eu.happycoders.shop.application.port.in.team.AddPlayerToTeamUseCase;
import eu.happycoders.shop.application.port.in.team.TeamtNotFoundException;
import eu.happycoders.shop.application.port.out.persistence.CartRepository;
import eu.happycoders.shop.application.port.out.persistence.ProductRepository;
import eu.happycoders.shop.application.port.out.persistence.TeamRepository;
import eu.happycoders.shop.model.cart.Cart;
import eu.happycoders.shop.model.cart.NotEnoughItemsInStockException;
import eu.happycoders.shop.model.customer.CustomerId;
import eu.happycoders.shop.model.player.Player;
import eu.happycoders.shop.model.product.Product;
import eu.happycoders.shop.model.product.ProductId;
import eu.happycoders.shop.model.team.Team;
import eu.happycoders.shop.model.team.TeamHasMaxNumberOfPlayer;

import java.util.List;
import java.util.Objects;



public class AddPlayerToTeamService implements AddPlayerToTeamUseCase {

    private final TeamRepository teamRepository;

    public AddPlayerToTeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team addPlayerToTeam(String name, Player player) throws TeamtNotFoundException, TeamHasMaxNumberOfPlayer {
        Objects.requireNonNull(name, "'team name' must not be null");

        Team team = teamRepository.findByTeamName(name).orElseThrow(TeamtNotFoundException::new);
        team.addPlayer(player);
        teamRepository.save(team);

        return team;
    }


}

