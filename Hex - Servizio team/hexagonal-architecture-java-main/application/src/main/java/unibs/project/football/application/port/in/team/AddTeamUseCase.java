package unibs.project.football.application.port.in.team;

import unibs.project.football.model.team.Team;

public interface AddTeamUseCase {
  Team AddTeam(String name) throws TeamAlreadyExistException;
}
