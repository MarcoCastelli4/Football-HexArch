package unibs.project.football.team.adapter;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unibs.project.football.team.player.Injurie;
import unibs.project.football.team.player.OldTeam;

@Getter
@AllArgsConstructor
@NoArgsConstructor // Needed for deserialization
public class PlayerDTO {

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
  private ArrayList<Injurie> injuries;

  @JsonProperty("oldTeams")
  private ArrayList<OldTeam> oldTeams;

  public String getTeam() {
    return team;
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

  public ArrayList<OldTeam> getOldTeams() {
    return oldTeams;
  }

  public ArrayList<Injurie> getInjuries() {
    return injuries;
  }
}
