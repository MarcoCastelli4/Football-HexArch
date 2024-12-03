package unibs.project.football.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor // Needed for deserialization
public class Player {

  @JsonProperty("team")
  private String team;

  @JsonProperty("name")
  private String name;

  @JsonProperty("role")
  private String role;

  @JsonProperty("goals")
  private int goal;

  @JsonProperty("age")
  private int age;

  @JsonProperty("gender")
  private String gender;

  @JsonProperty("height")
  private int height;

  @JsonProperty("weight")
  private int weight;

  @JsonProperty("injuries")
  private Map<Integer, String> injuries;

  @JsonProperty("oldTeams")
  private Map<Integer, String> oldTeams;

  public String getTeam() {
    return team;
  }

  public Map<Integer, String> getInjuries() {
    return injuries;
  }

  public int getWeight() {
    return weight;
  }

  public int getHeight() {
    return height;
  }

  public String getGender() {
    return gender;
  }

  public int getAge() {
    return age;
  }

  public int getGoal() {
    return goal;
  }

  public String getRole() {
    return role;
  }

  public String getName() {
    return name;
  }

  public Map<Integer, String> getOldTeams() {
    return oldTeams;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Player player = (Player) o;
    return age == player.age
        && Objects.equals(getName(), player.getName())
        && Objects.equals(getRole(), player.getRole())
        && Objects.equals(getTeam(), player.getTeam())
        && Objects.equals(getGoal(), player.getGoal())
        && Objects.equals(getAge(), player.getAge())
        && Objects.equals(getGender(), player.getGender())
        && Objects.equals(getHeight(), player.getHeight())
        && Objects.equals(getWeight(), player.getWeight())
        && Objects.equals(getInjuries(), player.getInjuries())
        && Objects.equals(getOldTeams(), player.getOldTeams());
  }
}
