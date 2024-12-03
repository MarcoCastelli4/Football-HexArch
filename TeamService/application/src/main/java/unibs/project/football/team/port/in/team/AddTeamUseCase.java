package unibs.project.football.team.port.in.team;

import unibs.project.football.team.team.Team;

public interface AddTeamUseCase {
  Team AddTeam(String name) throws TeamAlreadyExistException;
}
