package eu.happycoders.shop.adapter.in.rest.team;

import eu.happycoders.shop.model.player.Player;

/**
 * Model class for returning a shopping cart line item via REST API.
 *
 * @author Sven Woltmann
 */
public record PlayerWebModel(
    String name,String role) {

  public static PlayerWebModel fromDomainModel(Player player) {

    return new PlayerWebModel(
        player.name(),player.role().toString());
  }
}
