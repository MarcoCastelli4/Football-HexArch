package eu.happycoders.shop.bootstrap;

import eu.happycoders.shop.adapter.in.rest.cart.AddToCartController;
import eu.happycoders.shop.adapter.in.rest.cart.EmptyCartController;
import eu.happycoders.shop.adapter.in.rest.cart.GetCartController;
import eu.happycoders.shop.adapter.in.rest.message.messageController;
import eu.happycoders.shop.adapter.in.rest.team.GetTeamController;
import eu.happycoders.shop.adapter.in.rest.team.AddToPlayerToTeamController;
import eu.happycoders.shop.adapter.in.rest.product.FindProductsController;
import eu.happycoders.shop.adapter.in.rest.team.AddToPlayerToTeamController;
import eu.happycoders.shop.adapter.in.rest.team.GetTeamController;
import eu.happycoders.shop.adapter.out.persistence.inmemory.InMemoryCartRepository;
import eu.happycoders.shop.adapter.out.persistence.inmemory.InMemoryProductRepository;
import eu.happycoders.shop.adapter.out.persistence.inmemory.inMemoryMessageRepository;
import eu.happycoders.shop.adapter.out.persistence.inmemory.InMemoryTeamRepository;
import eu.happycoders.shop.adapter.out.persistence.jpa.EntityManagerFactoryFactory;
import eu.happycoders.shop.adapter.out.persistence.jpa.JpaCartRepository;
import eu.happycoders.shop.adapter.out.persistence.jpa.JpaProductRepository;
import eu.happycoders.shop.application.port.in.cart.AddToCartUseCase;
import eu.happycoders.shop.application.port.in.cart.EmptyCartUseCase;
import eu.happycoders.shop.application.port.in.cart.GetCartUseCase;
import eu.happycoders.shop.application.port.in.message.messageUseCase;
import eu.happycoders.shop.application.port.in.team.GetPlayersUseCase;
import eu.happycoders.shop.application.port.in.team.AddPlayerToTeamUseCase;
import eu.happycoders.shop.application.port.in.product.FindProductsUseCase;
import eu.happycoders.shop.application.port.out.persistence.CartRepository;
import eu.happycoders.shop.application.port.out.persistence.messageRepository;
import eu.happycoders.shop.application.port.out.persistence.ProductRepository;
import eu.happycoders.shop.application.port.out.persistence.TeamRepository;
import eu.happycoders.shop.application.service.cart.AddToCartService;
import eu.happycoders.shop.application.service.cart.EmptyCartService;
import eu.happycoders.shop.application.service.cart.GetCartService;
import eu.happycoders.shop.application.service.message.messageService;
import eu.happycoders.shop.application.service.Team.AddPlayerToTeamService;
import eu.happycoders.shop.application.service.Team.GetPlayersService;
import eu.happycoders.shop.application.service.product.FindProductsService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.ws.rs.core.Application;
import java.util.Set;

/**
 * The application configuration for the Undertow server. Evaluates the persistence configuration,
 * instantiates the appropriate adapters and use cases, and wires them.
 *
 * @author Sven Woltmann
 */
public class RestEasyUndertowShopApplication extends Application {

  private CartRepository cartRepository;
  private ProductRepository productRepository;
  private messageRepository messageRepository;
  private TeamRepository teamRepository;

  // We're encouraged to use "automatic discovery of resources", but I want to define them manually.
  @SuppressWarnings("deprecation")
  @Override
  public Set<Object> getSingletons() {
    initPersistenceAdapters();
    return Set.of(
        addToCartController(),
        getCartController(),
        emptyCartController(),
        findProductsController(),
        messageController(),
        addToPlayerToTeamController(), getTeamController());
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
    cartRepository = new InMemoryCartRepository();
    productRepository = new InMemoryProductRepository();
    messageRepository=new inMemoryMessageRepository();
    teamRepository = new InMemoryTeamRepository();
  }

  // The EntityManagerFactory doesn't need to get closed before the application is stopped
  @SuppressWarnings("PMD.CloseResource")
  private void initMySqlAdapters() {
    EntityManagerFactory entityManagerFactory =
        EntityManagerFactoryFactory.createMySqlEntityManagerFactory(
            "jdbc:mysql://localhost:3306/shop", "root", "test");

    cartRepository = new JpaCartRepository(entityManagerFactory);
    productRepository = new JpaProductRepository(entityManagerFactory);
  }

  private AddToCartController addToCartController() {
    AddToCartUseCase addToCartUseCase = new AddToCartService(cartRepository, productRepository);
    return new AddToCartController(addToCartUseCase);
  }

  private GetCartController getCartController() {
    GetCartUseCase getCartUseCase = new GetCartService(cartRepository);
    return new GetCartController(getCartUseCase);
  }

  private EmptyCartController emptyCartController() {
    EmptyCartUseCase emptyCartUseCase = new EmptyCartService(cartRepository);
    return new EmptyCartController(emptyCartUseCase);
  }

  private FindProductsController findProductsController() {
    FindProductsUseCase findProductsUseCase = new FindProductsService(productRepository);
    return new FindProductsController(findProductsUseCase);
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

}
