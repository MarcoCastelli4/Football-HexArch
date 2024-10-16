package unibs.project.football.bootstrap;

import unibs.project.football.adapter.in.rest.message.messageController;
import unibs.project.football.adapter.in.rest.team.AddTeamController;
import unibs.project.football.adapter.in.rest.team.AddToPlayerToTeamController;
import unibs.project.football.adapter.in.rest.team.GetTeamController;
import unibs.project.football.adapter.out.persistence.inmemory.InMemoryTeamRepository;
import unibs.project.football.adapter.out.persistence.inmemory.inMemoryMessageRepository;
import unibs.project.football.application.port.in.message.messageUseCase;
import unibs.project.football.application.port.in.team.AddPlayerToTeamUseCase;
import unibs.project.football.application.port.in.team.AddTeamUseCase;
import unibs.project.football.application.port.in.team.GetPlayersUseCase;
import unibs.project.football.application.port.out.persistence.TeamRepository;
import unibs.project.football.application.port.out.persistence.messageRepository;
import unibs.project.football.application.service.Team.AddPlayerToTeamService;
import unibs.project.football.application.service.Team.AddTeamService;
import unibs.project.football.application.service.Team.GetPlayersService;
import unibs.project.football.application.service.message.messageService;
import jakarta.ws.rs.core.Application;
import java.util.Set;

/**
 * The application configuration for the Undertow server. Evaluates the persistence configuration,
 * instantiates the appropriate adapters and use cases, and wires them.
 */
public class RestEasyUndertowShopApplication extends Application {
  private messageRepository messageRepository;
  private TeamRepository teamRepository;

  // We're encouraged to use "automatic discovery of resources", but I want to define them manually.
  @SuppressWarnings("deprecation")
  @Override
  public Set<Object> getSingletons() {
    initPersistenceAdapters();
    return Set.of(
        messageController(),
        addToPlayerToTeamController(),
        getTeamController(),
        addTeamController());
  }

  private void initPersistenceAdapters() {
    String persistence = System.getProperty("persistence", "inmemory");
    switch (persistence) {
      case "inmemory" -> initInMemoryAdapters();
      case "mysql" -> initMySqlAdapters();
      default -> throw new IllegalArgumentException(
          "Invalid 'persistence' property: '%s' (allowed: 'inmemory', 'mysql')"
              .formatted(persistence));
    }
  }

  private void initInMemoryAdapters() {
    messageRepository = new inMemoryMessageRepository();
    teamRepository = new InMemoryTeamRepository();
  }

  // The EntityManagerFactory doesn't need to get closed before the application is stopped
  @SuppressWarnings("PMD.CloseResource")
  private void initMySqlAdapters() {
    /*
    EntityManagerFactory entityManagerFactory =
        EntityManagerFactoryFactory.createMySqlEntityManagerFactory(
            "jdbc:mysql://localhost:3306/shop", "root", "test");*/

  }

  private messageController messageController() {
    messageUseCase findMessageUseCase = new messageService(messageRepository);
    return new messageController(findMessageUseCase);
  }

  private AddToPlayerToTeamController addToPlayerToTeamController() {
    AddPlayerToTeamUseCase addPlayerToTeamUseCase = new AddPlayerToTeamService(teamRepository);
    return new AddToPlayerToTeamController(addPlayerToTeamUseCase);
  }

  private GetTeamController getTeamController() {
    GetPlayersUseCase m = new GetPlayersService(teamRepository);
    return new GetTeamController(m);
  }

  private AddTeamController addTeamController() {
    AddTeamUseCase a = new AddTeamService(teamRepository);
    return new AddTeamController(a);
  }
}
