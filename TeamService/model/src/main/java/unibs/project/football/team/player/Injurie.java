package unibs.project.football.team.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor // Needed for deserialization
public class Injurie {

  @JsonProperty("year")
  private Integer year;

  @JsonProperty("type")
  private String type;

  public String getType() {
    return type;
  }

  public Integer getYear() {
    return year;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Injurie injuries = (Injurie) o;
    return Objects.equals(getType(), injuries.getType())
        && Objects.equals(getYear(), injuries.getYear());
  }
}
