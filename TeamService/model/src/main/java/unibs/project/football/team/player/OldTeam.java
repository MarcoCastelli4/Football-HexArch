package unibs.project.football.team.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor // Needed for deserialization
public class OldTeam {

  @JsonProperty("year")
  private Integer year;

  @JsonProperty("team")
  private String team;

  public String getTeam() {
    return team;
  }

  public Integer getYear() {
    return year;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OldTeam oldTeams = (OldTeam) o;
    return Objects.equals(getYear(), oldTeams.getYear())
        && Objects.equals(getTeam(), oldTeams.getTeam());
  }
}
