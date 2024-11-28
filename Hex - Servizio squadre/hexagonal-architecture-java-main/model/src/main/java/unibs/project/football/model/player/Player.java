package unibs.project.football.model.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor  // Needed for deserialization
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
  private Map<Integer,String> injuries;

  @JsonProperty("oldTeams")
  private Map<Integer,String> oldTeams;


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
  public boolean equals(Object obj) {
    Player other = (Player) obj;
    return Objects.equals(name, other.name);
  }
}

