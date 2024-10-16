package unibs.project.football.adapter.out.persistence.jpa;

import unibs.project.football.model.player.Player;
import unibs.project.football.model.team.Team;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

final class TeamMapper {

  private TeamMapper() {}

  // Convert Team (domain model) to TeamJpaEntity (persistence entity)
  static TeamJpaEntity toJpaEntity(Team team) {
    TeamJpaEntity jpaEntity = new TeamJpaEntity();

    // Assuming Team has fields like id and name
    jpaEntity.setName(team.name());

    // Convert the list of Players to list of PlayerJpaEntities
    List<PlayerJpaEntity> playerJpaEntities =
        team.playerOfTeam().stream().map(PlayerMapper::toJpaEntity).collect(Collectors.toList());

    jpaEntity.setPlayers(playerJpaEntities);

    return jpaEntity;
  }

  // Convert TeamJpaEntity (persistence entity) to Team (domain model)
  static Team toModelEntity(TeamJpaEntity jpaEntity) {
    // Convert the list of PlayerJpaEntities to list of Players
    List<Player> players =
        jpaEntity.getPlayers().stream()
            .map(PlayerMapper::toModelEntity)
            .collect(Collectors.toList());

    return new Team(jpaEntity.getName(), players);
  }

  // Convert an optional TeamJpaEntity to an optional Team
  static Optional<Team> toModelEntityOptional(TeamJpaEntity jpaEntity) {
    return Optional.ofNullable(jpaEntity).map(TeamMapper::toModelEntity);
  }

  // Convert a list of TeamJpaEntity to a list of Team
  static List<Team> toModelEntities(List<TeamJpaEntity> jpaEntities) {
    return jpaEntities.stream().map(TeamMapper::toModelEntity).toList();
  }

  // Convert a list of Team (domain) to a list of TeamJpaEntity (persistence)
  static List<TeamJpaEntity> toJpaEntities(List<Team> teams) {
    return teams.stream().map(TeamMapper::toJpaEntity).toList();
  }
}
