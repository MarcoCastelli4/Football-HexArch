package unibs.project.football.team.adapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

  public void setTeam(String team) {
    this.team = team;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public void setGoal(int goal) {
    this.goal = goal;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public void setInjuries(Map<Integer, String> injuries) {
    this.injuries = injuries;
  }

  public void setOldTeams(Map<Integer, String> oldTeams) {
    this.oldTeams = oldTeams;
  }
}
