package unibs.project.football.model.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@RequiredArgsConstructor
public class Player {

  @Getter private String name;
  @Getter private String role;

  public Player(String name, String role) {
    this.role = role;
    this.name = name;
  }

  public String toString() {
    return name + ' ' + role;
  }
}
