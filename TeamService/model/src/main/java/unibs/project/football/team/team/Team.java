package unibs.project.football.team.team;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Team {

  @JsonProperty("name")
  private String name;

  public String getName() {
    return name;
  }
}
