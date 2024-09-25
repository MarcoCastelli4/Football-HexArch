package eu.happycoders.shop.bootstrap;

import eu.happycoders.shop.adapter.in.rest.message.messageController;
import eu.happycoders.shop.adapter.in.rest.team.AddTeamController;
import eu.happycoders.shop.adapter.in.rest.team.GetTeamController;
import eu.happycoders.shop.adapter.in.rest.team.AddToPlayerToTeamController;
import eu.happycoders.shop.adapter.out.persistence.inmemory.inMemoryMessageRepository;
import eu.happycoders.shop.adapter.out.persistence.inmemory.InMemoryTeamRepository;
import eu.happycoders.shop.application.port.in.message.messageUseCase;
import eu.happycoders.shop.application.port.in.team.AddTeamUseCase;
import eu.happycoders.shop.application.port.in.team.GetPlayersUseCase;
import eu.happycoders.shop.application.port.in.team.AddPlayerToTeamUseCase;
import eu.happycoders.shop.application.port.out.persistence.messageRepository;
import eu.happycoders.shop.application.port.out.persistence.TeamRepository;
import eu.happycoders.shop.application.service.Team.AddTeamService;
import eu.happycoders.shop.application.service.message.messageService;
import eu.happycoders.shop.application.service.Team.AddPlayerToTeamService;
import eu.happycoders.shop.application.service.Team.GetPlayersService;

import jakarta.persistence.EntityManagerFactory;
import jakarta.ws.rs.core.Application;
import java.util.Set;

/**
 * The application configuration for the Undertow server. Evaluates the persistence configuration,
 * instantiates the appropriate adapters and use cases, and wires them.
 *
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
        addToPlayerToTeamController(), getTeamController(),addTeamController());
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
    messageRepository=new inMemoryMessageRepository();
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
    GetPlayersUseCase m=new GetPlayersService(teamRepository);
    return new GetTeamController(m);
  }

  private AddTeamController addTeamController(){
    AddTeamUseCase a=new AddTeamService(teamRepository);
    return new AddTeamController(a);
  }

}
