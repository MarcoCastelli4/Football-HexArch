package unibs.project.football.team.adapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unibs.project.football.team.player.Injurie;
import unibs.project.football.team.player.OldTeam;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
@NoArgsConstructor // Needed for deserialization
public class PlayerDTOEr {

  @JsonProperty("team")
  private int team;


  @JsonProperty("height")
  private int height;

  @JsonProperty("weight")
  private int weight;

  @JsonProperty("sex")
  private String sex;


  public int getTeam() {
    return team;
  }

  public int getWeight() {
    return weight;
  }

  public int getHeight() {
    return height;
  }

  public String getGender() {
    return sex;
  }



}
