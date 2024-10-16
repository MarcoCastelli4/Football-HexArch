package unibs.project.football.adapter.out.persistence.jpa;

import unibs.project.football.model.player.Player;
import java.util.List;
import java.util.Optional;

/** Maps a model product to a JPA product and vice versa. */
final class PlayerMapper {

  private PlayerMapper() {}

  // Convert Player (domain model) to PlayerJpaEntity (persistence entity)
  static PlayerJpaEntity toJpaEntity(Player player) {
    PlayerJpaEntity jpaEntity = new PlayerJpaEntity();

    // Assuming Player has fields like id, name, etc.
    jpaEntity.setRole(player.role());
    jpaEntity.setName(player.name());

    // If there are any other fields in Player, add them here.
    // Example: jpaEntity.setAge(player.getAge());

    return jpaEntity;
  }

  // Convert PlayerJpaEntity (persistence entity) to Player (domain model)
  static Player toModelEntity(PlayerJpaEntity jpaEntity) {
    return new Player(
        jpaEntity.getRole(), jpaEntity.getName()
        // Include other fields as necessary.
        // Example: jpaEntity.getAge()
        );
  }

  // Convert an optional PlayerJpaEntity to an optional Player
  static Optional<Player> toModelEntityOptional(PlayerJpaEntity jpaEntity) {
    return Optional.ofNullable(jpaEntity).map(PlayerMapper::toModelEntity);
  }

  // Convert a list of PlayerJpaEntity to a list of Player
  static List<Player> toModelEntities(List<PlayerJpaEntity> jpaEntities) {
    return jpaEntities.stream().map(PlayerMapper::toModelEntity).toList();
  }

  // Convert a list of Player (domain) to a list of PlayerJpaEntity (persistence)
  static List<PlayerJpaEntity> toJpaEntities(List<Player> players) {
    return players.stream().map(PlayerMapper::toJpaEntity).toList();
  }
}
