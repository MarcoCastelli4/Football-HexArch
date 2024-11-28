package unibs.project.football.application.port.out;

import unibs.project.football.model.message.Message;
import unibs.project.football.model.team.Team;

import java.util.Optional;

public interface GetGoalPlayer {
 Integer getGoalPlayer(String playerName);
}
